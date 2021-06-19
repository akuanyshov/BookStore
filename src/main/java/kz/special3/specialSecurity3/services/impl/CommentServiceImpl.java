package kz.special3.specialSecurity3.services.impl;

import kz.special3.specialSecurity3.entities.*;
import kz.special3.specialSecurity3.repositories.ArtRepository;
import kz.special3.specialSecurity3.repositories.ArtRepository;
import kz.special3.specialSecurity3.repositories.BasketRepository;
import kz.special3.specialSecurity3.repositories.CommentRepository;
import kz.special3.specialSecurity3.services.ArtService;
import kz.special3.specialSecurity3.services.BasketService;
import kz.special3.specialSecurity3.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comments> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comments saveComment(Comments comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comments getComment(Long id) {
        return commentRepository.getOne(id);
    }

    @Override
    public void deleteComment(Comments comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comments addComment(Comments comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comments> findAllByBook(Book book) {
        return commentRepository.findAllByBookOrderByAddedDateDesc(book);
    }

    @Override
    public List<Comments> findAllByArt(Art art) {
        return commentRepository.findAllByArtOrderByAddedDateDesc(art);
    }

    @Override
    public List<Comments> findAllBySchoolSupplies(SchoolSuplies schoolSuplies) {
        return commentRepository.findAllBySchoolSuppliesOrderByAddedDateDesc(schoolSuplies);
    }

}
