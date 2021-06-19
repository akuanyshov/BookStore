package kz.special3.specialSecurity3.services.impl;

import kz.special3.specialSecurity3.entities.Art;
import kz.special3.specialSecurity3.entities.Baskets;
import kz.special3.specialSecurity3.repositories.ArtRepository;
import kz.special3.specialSecurity3.repositories.ArtRepository;
import kz.special3.specialSecurity3.repositories.BasketRepository;
import kz.special3.specialSecurity3.services.ArtService;
import kz.special3.specialSecurity3.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketRepository basketsRepository;

    @Override
    public List<Baskets> getAllBasket() {
        return basketsRepository.findAll();
    }

    @Override
    public Baskets saveBasket(Baskets basket) {
        return basketsRepository.save(basket);
    }

    @Override
    public Baskets getBaskets(Long id) {
        return basketsRepository.getOne(id);
    }

    @Override
    public void deleteBaskets(Baskets basket) {
        basketsRepository.delete(basket);
    }

    @Override
    public Baskets addBaskets(Baskets basket) {
        return basketsRepository.save(basket);
    }
}
