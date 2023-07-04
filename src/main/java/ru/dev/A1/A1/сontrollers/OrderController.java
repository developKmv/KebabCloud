package ru.dev.A1.A1.сontrollers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.dev.A1.A1.dao.JpaDAOHibernate;
import ru.dev.A1.A1.data.OrderRepository;
import ru.dev.A1.A1.models.KebabOrder;
import ru.dev.A1.A1.models.User;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("kebabOrder")
public class OrderController {
    //private OrderRepository orderRepository;
    private JpaDAOHibernate repo;
    @Autowired
    public OrderController(JpaDAOHibernate repo){
        this.repo = repo;
    }
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid KebabOrder kebabOrder, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal User user) {

        if(errors.hasErrors()) return "orderForm";

        kebabOrder.setUser(user);

        log.info("Order submitted:{}", kebabOrder);
        //orderRepository.save(kebabOrder);
        repo.insertKebabOrder(kebabOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
