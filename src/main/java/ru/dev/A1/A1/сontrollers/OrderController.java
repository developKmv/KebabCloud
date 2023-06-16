package ru.dev.A1.A1.сontrollers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.dev.A1.A1.data.OrderRepository;
import ru.dev.A1.A1.models.KebabOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("kebabOrder")
public class OrderController {
    private OrderRepository orderRepository;
    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid KebabOrder kebabOrder, Errors errors, SessionStatus sessionStatus) {

        if(errors.hasErrors()) return "orderForm";

        log.info("Order submitted:{}", kebabOrder);
        orderRepository.save(kebabOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
