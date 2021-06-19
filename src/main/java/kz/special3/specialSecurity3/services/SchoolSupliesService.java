package kz.special3.specialSecurity3.services;

import kz.special3.specialSecurity3.entities.Book;
import kz.special3.specialSecurity3.entities.SchoolSuplies;

import java.util.ArrayList;

public interface SchoolSupliesService {
    ArrayList<SchoolSuplies> getAllSchoolSuplies();
    void saveCourse (SchoolSuplies schoolSuplies);
    SchoolSuplies getSchoolSupliesById(Long id);
    SchoolSuplies saveSchoolSuplies(SchoolSuplies schoolSuplies);
}
