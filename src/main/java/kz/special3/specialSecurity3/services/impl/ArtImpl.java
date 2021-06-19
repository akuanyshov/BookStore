package kz.special3.specialSecurity3.services.impl;

import kz.special3.specialSecurity3.entities.Art;
import kz.special3.specialSecurity3.repositories.ArtRepository;
import kz.special3.specialSecurity3.repositories.ArtRepository;
import kz.special3.specialSecurity3.services.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArtImpl implements ArtService {
    @Autowired
    private ArtRepository artRepository;

    @Override
    public ArrayList<Art> getAllArt() {
        return (ArrayList<Art>) artRepository.findAll();
    }

    @Override
    public void saveCourse(Art art) {
        artRepository.save(art);
    }

    @Override
    public Art getArtById(Long id) {
        return artRepository.getOne(id);
    }

    @Override
    public Art saveArt(Art art) {
        return artRepository.save(art);
    }
}
