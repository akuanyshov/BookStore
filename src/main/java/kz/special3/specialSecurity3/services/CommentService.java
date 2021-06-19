package kz.special3.specialSecurity3.services;

import kz.special3.specialSecurity3.entities.Art;
import kz.special3.specialSecurity3.entities.Comments;
import kz.special3.specialSecurity3.entities.Book;
import kz.special3.specialSecurity3.entities.SchoolSuplies;

import java.util.List;

public interface CommentService {
    List<Comments> getAllComments();
    Comments saveComment(Comments comment);
    Comments getComment(Long id);
    void deleteComment(Comments comment);
    Comments addComment(Comments comment);
    List<Comments> findAllByBook(Book book);
    List<Comments> findAllByArt(Art art);
    List<Comments> findAllBySchoolSupplies(SchoolSuplies schoolSuplies);
}
