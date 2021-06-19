package kz.special3.specialSecurity3.services.impl;

import kz.special3.specialSecurity3.entities.Book;
import kz.special3.specialSecurity3.entities.SchoolSuplies;
import kz.special3.specialSecurity3.repositories.BookRepository;
import kz.special3.specialSecurity3.repositories.SchoolSupliesRepository;
import kz.special3.specialSecurity3.services.BookService;
import kz.special3.specialSecurity3.services.SchoolSupliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SchoolSupliesServiceImpl implements SchoolSupliesService {
    @Autowired
    private SchoolSupliesRepository schoolSupliesRepository;

    @Override
    public ArrayList<SchoolSuplies> getAllSchoolSuplies() {
        return (ArrayList<SchoolSuplies>) schoolSupliesRepository.findAll();
    }

    @Override
    public void saveCourse(SchoolSuplies schoolSuplies) {
        schoolSupliesRepository.save(schoolSuplies);
    }

    @Override
    public SchoolSuplies getSchoolSupliesById(Long id) {
        return schoolSupliesRepository.getOne(id);
    }

    @Override
    public SchoolSuplies saveSchoolSuplies(SchoolSuplies schoolSuplies) {
        return schoolSupliesRepository.save(schoolSuplies);
    }
}
