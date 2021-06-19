package kz.special3.specialSecurity3.services;

import kz.special3.specialSecurity3.entities.Baskets;

import java.util.List;

public interface BasketService {
    List<Baskets> getAllBasket();
    Baskets saveBasket(Baskets basket);
    Baskets getBaskets(Long id);
    void deleteBaskets(Baskets basket);
    Baskets addBaskets(Baskets basket);
}
