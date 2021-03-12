package com.example.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.persistence.entities.Coupon;
import com.example.persistence.entities.MenuItem;
import com.example.persistence.entities.Order;
import com.example.persistence.entities.OrderItem;
import com.example.persistence.repos.CouponRepository;
import com.example.persistence.repos.MenuItemRepository;
import com.example.persistence.repos.OrderItemRepository;
import com.example.persistence.repos.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderItemRepository itemRepo;
	@Autowired
	private MenuItemRepository menuItemRepo;
	@Autowired
	private CouponRepository couponRepo;
	
	@PostMapping("/postorder")
	public Order postOrder(@RequestBody Order order) {

		Set<OrderItem> items = new HashSet<>();
		order.getItems().forEach(i -> items.add(i));

		// compute the totals
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal totalPlusTax = BigDecimal.ZERO;

		for (OrderItem i : items) {
			MenuItem menuItem = menuItemRepo.findById(i.getMenuItem().getId()).get();
			i.setMenuItem(menuItem);
			total = total.add(menuItem.getPrice().multiply(new BigDecimal(i.getQuantity())));

			BigDecimal priceTimesTax = menuItem.getPrice().multiply(menuItem.getTax());
			BigDecimal pricePluspriceTimesTax = menuItem.getPrice().add(priceTimesTax);
			BigDecimal pricePluspriceTimesTaxTimesQuantity = pricePluspriceTimesTax
					.multiply(new BigDecimal(i.getQuantity()));
			totalPlusTax = totalPlusTax.add(pricePluspriceTimesTaxTimesQuantity);
		}

		order.setTotal(total.setScale(2, RoundingMode.HALF_EVEN));
		order.setTotalPlusTax(totalPlusTax.setScale(2, RoundingMode.HALF_EVEN));
		if (order.getCoupon() != null) {
			BigDecimal couponPercent = new BigDecimal(0.1);
			BigDecimal couponAmount = totalPlusTax.multiply(couponPercent);
			order.setTotalPlusTaxLessCoupon(totalPlusTax.subtract(couponAmount).setScale(2, RoundingMode.HALF_EVEN));
		} else {
			order.setTotalPlusTaxLessCoupon(totalPlusTax.setScale(2, RoundingMode.HALF_EVEN));
		}

		// save
		if (order.getCoupon() != null) {
			Coupon coupon = couponRepo.findById(order.getCoupon().getCode()).get();
			coupon.setUsed(true);
			couponRepo.save(coupon);
		}
		Order response = orderRepo.save(order);
		for (OrderItem i : items) {
			i.setOrder(response);
			itemRepo.save(i);
		}

		return orderRepo.findById(response.getId()).get();
	}
}
