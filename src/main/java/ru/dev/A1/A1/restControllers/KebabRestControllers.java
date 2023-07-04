package ru.dev.A1.A1.restControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.A1.A1.dao.JpaDAOHibernate;
import ru.dev.A1.A1.data.OrderRepository;
import ru.dev.A1.A1.models.Kebab;
import ru.dev.A1.A1.models.KebabOrder;
import ru.dev.A1.A1.еmp.SimpleJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping(path = "/api/kebab", produces = "application/json")
//@CrossOrigin(origins="http://localhost:56075")
public class KebabRestControllers {
    private JpaDAOHibernate repo;
    private OrderRepository orderRepository;

    @Autowired
    public KebabRestControllers(JpaDAOHibernate repo,OrderRepository orderRepository) {
        this.repo = repo;
        this.orderRepository = orderRepository;
    }

    @GetMapping(params = "recent")
    public List<Kebab> recentKebab() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return repo.getAllKebab();
    }
    @GetMapping("/allOrders")
    public List<KebabOrder> getAllOrders(){
        ArrayList<KebabOrder> result = new ArrayList<>();
        orderRepository.findAll().forEach(result::add);

        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kebab> getKebabById(@PathVariable("id") Long id) {

        Optional<Kebab> kebabOpt = Optional.ofNullable(repo.getKebabById(id));
        log.info("find id: {}",kebabOpt.get());

        if (kebabOpt.isPresent())
        {
            return new ResponseEntity<>(kebabOpt.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Kebab postTaco(@RequestBody Kebab kebab) {
        return repo.insertKebab(kebab);
    }

    @PutMapping(path="/{orderId}", consumes="application/json")
    public KebabOrder putOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody KebabOrder order) {
        order.setId(orderId);

        return orderRepository.save(order);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public KebabOrder patchOrder(@PathVariable("orderId") Long orderId,
                                @RequestBody KebabOrder patch) {
        KebabOrder order = orderRepository.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }


}
