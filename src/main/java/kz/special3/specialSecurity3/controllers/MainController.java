package kz.special3.specialSecurity3.controllers;


import kz.special3.specialSecurity3.entities.*;
import kz.special3.specialSecurity3.repositories.*;
import kz.special3.specialSecurity3.services.*;
import kz.special3.specialSecurity3.services.impl.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private SchoolSupliesServiceImpl schoolSupliesService;

    @Autowired
    private ArtImpl artService;

    @Autowired
    private SchoolSupliesRepository schoolSupliesRepository;

    @Autowired
    private ArtRepository artRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BasketServiceImpl basketService;

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BasketRepository basketRepository;

    @GetMapping(value = "/")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        ArrayList<Book> books = bookService.getAllBooks();
        if (books != null) {
            model.addAttribute("allBooks", books);
        }
        ArrayList<SchoolSuplies> schoolSuplies = schoolSupliesService.getAllSchoolSuplies();
        if (schoolSuplies != null) {
            model.addAttribute("allSchoolsuplies", schoolSuplies);
        }
        ArrayList<Art> art = artService.getAllArt();
        if (art != null) {
            model.addAttribute("allArt", art);
        }
        return "index";
    }

    @GetMapping(value = "/signInPage")
    public String signin(Model model, HttpServletRequest request) {
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        return "signInPage";
    }


    @GetMapping(value = "/profile")
    public String profile(Model model, HttpServletRequest request) {
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        return "profile";
    }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String adminPanel(Model model, HttpServletRequest request) {
        ArrayList<Countries> allCountries = (ArrayList<Countries>) countryRepository.findAll();
        ArrayList<Book> allBooks = (ArrayList<Book>) bookRepository.findAll();
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("allCountries", allCountries);
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        return "admin";
    }

    @GetMapping(value = "/schoolsuplies")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String schoolsupliesPanel(Model model, HttpServletRequest request) {
        ArrayList<Countries> allCountries = (ArrayList<Countries>) countryRepository.findAll();
        ArrayList<SchoolSuplies> allSchoolSuplies = (ArrayList<SchoolSuplies>) schoolSupliesRepository.findAll();
        model.addAttribute("allSchoolsuplies", allSchoolSuplies);
        model.addAttribute("allCountries", allCountries);
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        return "schoolsuplies";
    }


    @GetMapping(value = "/art")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String artPanel(Model model, HttpServletRequest request) {
        ArrayList<Countries> allCountries = (ArrayList<Countries>) countryRepository.findAll();
        ArrayList<Art> allArt = (ArrayList<Art>) artRepository.findAll();
        model.addAttribute("allArt", allArt);
        model.addAttribute("allCountries", allCountries);
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        return "art";
    }

    @GetMapping(value = "/allproductsmain")
    public String allproductsmainPanel(Model model, HttpServletRequest request) {
        ArrayList<Countries> allCountries = (ArrayList<Countries>) countryRepository.findAll();
        ArrayList<Book> allBooks = (ArrayList<Book>) bookRepository.findAll();
        ArrayList<SchoolSuplies> allSchoolSuplies = (ArrayList<SchoolSuplies>) schoolSupliesRepository.findAll();
        ArrayList<Art> allArt = (ArrayList<Art>) artRepository.findAll();
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("allSchoolsuplies", allSchoolSuplies);
        model.addAttribute("allArt", allArt);
        model.addAttribute("allCountries", allCountries);
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        return "allproductsmain";
    }

    @GetMapping(value = "/allbookmain")
    public String allbookmainPanel(Model model, HttpServletRequest request) {
        ArrayList<Countries> allCountries = (ArrayList<Countries>) countryRepository.findAll();
        ArrayList<Book> allBooks = (ArrayList<Book>) bookRepository.findAll();
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("allCountries", allCountries);
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        return "allbookmain";
    }

    @GetMapping(value = "/allschoolsupliesmain")
    public String allschoolsupliesmainPanel(Model model, HttpServletRequest request) {
        ArrayList<Countries> allCountries = (ArrayList<Countries>) countryRepository.findAll();
        ArrayList<SchoolSuplies> allSchoolSuplies = (ArrayList<SchoolSuplies>) schoolSupliesRepository.findAll();
        model.addAttribute("allSchoolsuplies", allSchoolSuplies);
        model.addAttribute("allCountries", allCountries);
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        return "allschoolsupliesmain";
    }

    @GetMapping(value = "/allartmain")
    public String allartmainPanel(Model model, HttpServletRequest request) {
        ArrayList<Countries> allCountries = (ArrayList<Countries>) countryRepository.findAll();
        ArrayList<Art> allArt = (ArrayList<Art>) artRepository.findAll();
        model.addAttribute("allArt", allArt);
        model.addAttribute("allCountries", allCountries);
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        return "allartmain";
    }

    @GetMapping(value = "/moderator")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String moderPanel(Model model, HttpServletRequest request) {
        model.addAttribute("currentUser", getUser());
        ArrayList<Baskets> b = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        int s = 0;
        if (b != null) {
            for (int i = 0; i < b.size(); i++) {
                s += b.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        return "moderator";
    }

    @GetMapping(value = "/accessdenied")
    public String accessdenied(Model model, HttpServletRequest request) {
        model.addAttribute("currentUser", getUser());
        return "403";
    }

    @GetMapping(value = "/signUpPage")
    public String signUpPage(Model model) {
        model.addAttribute("currentUser", getUser());
        return "signUpPage";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @PostMapping(value = "/tosignup")
    public String signUp(@RequestParam(name = "user_email") String email,
                         @RequestParam(name = "user_password") String password,
                         @RequestParam(name = "re_user_password") String rePassword,
                         @RequestParam(name = "user_fullname") String fullName) {

        if (password.equals(rePassword)) {

            Users newUser = userService.registerUser(new Users(null, email, password, fullName, null));

            if (newUser != null && newUser.getId() != null) {
                return "redirect:/signInPage?success";
            }

        }

        return "redirect:/signInPage?error";

    }

    @PostMapping(value = "/updatepassword")
    public String updatePassword(@RequestParam(name = "old_password") String oldPass,
                                 @RequestParam(name = "new_password") String newPass,
                                 @RequestParam(name = "re_new_password") String reNewPass) {

        if (newPass.equals(reNewPass)) {

            if (userService.updatePassword(getUser(), oldPass, newPass)) {
                return "redirect:/profile?success";
            }
        }

        return "redirect:/profile?error";

    }

    @GetMapping(value = "/editbook/{id}")
    public String editbook(Model model, RedirectAttributes attributes,
                           @PathVariable(name = "id") Long id) {
        Book book = bookService.getBookById(id);
        System.out.println(book.toString());
        ArrayList<Countries> allCountries = (ArrayList<Countries>) countryRepository.findAll();
        model.addAttribute("book", book);
        model.addAttribute("allCountries", allCountries);
        System.out.println("BOOK");
        return "editbook";
    }

    @GetMapping(value = "/editschoolsuplies/{id}")
    public String editschoolsuplies(Model model, RedirectAttributes attributes,
                                    @PathVariable(name = "id") Long id) {
        SchoolSuplies schoolSuplies = schoolSupliesService.getSchoolSupliesById(id);
        System.out.println(schoolSuplies.toString());
        ArrayList<Countries> allCountries = (ArrayList<Countries>) countryRepository.findAll();
        model.addAttribute("schoolSuplies", schoolSuplies);
        model.addAttribute("allCountries", allCountries);
        System.out.println("SCHOOL SUPPLIES");
        return "editschoolsuplies";
    }

    @GetMapping(value = "/editart/{id}")
    public String editart(Model model, RedirectAttributes attributes,
                          @PathVariable(name = "id") Long id) {
        Art art = artService.getArtById(id);
        System.out.println(art.toString());
        ArrayList<Countries> allCountries = (ArrayList<Countries>) countryRepository.findAll();
        model.addAttribute("art", art);
        model.addAttribute("allCountries", allCountries);
        System.out.println("ART");
        return "editart";
    }

    @PostMapping(value = "/deletebook")
    public String deletebook(@RequestParam(name = "id") Long id) {
        Book book = bookService.getBookById(id);
        commentRepository.deleteAllByBook(book);
        bookRepository.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/deleteschoolsuplies")
    @PreAuthorize("isAuthenticated()")
    public String deleteschoolsuplies(@RequestParam(name = "id") Long id) {
        SchoolSuplies schoolSuplies = schoolSupliesService.getSchoolSupliesById(id);
        commentRepository.deleteAllBySchoolSupplies(schoolSuplies);
        schoolSupliesRepository.deleteById(id);
        return "redirect:/schoolsuplies";
    }

    @PostMapping(value = "/deleteart")
    @PreAuthorize("isAuthenticated()")
    public String deleteart(@RequestParam(name = "id") Long id) {
        Art art = artService.getArtById(id);
        commentRepository.deleteAllByArt(art);
        artRepository.deleteById(id);
        return "redirect:/art";
    }

    @PostMapping(value = "/editbook")
    @PreAuthorize("isAuthenticated()")
    public String editbook(Model model, RedirectAttributes redirectAttributes,
                           @RequestParam(name = "id") Long id,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "price") int price,
                           @RequestParam(name = "genre") String genre,
                           @RequestParam(name = "image") String image,
                           @RequestParam(name = "description") String description,
                           @RequestParam(name = "author") String author,
                           @RequestParam(name = "country_id") Long countryId) {
        Countries countries = countryRepository.getOne(countryId);
        Book book = bookService.getBookById(id);
        List<Book> allChapters = bookService.getAllBooks();
        book.setName(name);
        book.setPrice(price);
        book.setGenre(genre);
        book.setImage(image);
        book.setDescription(description);
        book.setAuthor(author);
        book.setCountry(countries);
        bookService.saveBook(book);
        return "redirect:/admin";
    }

    @PostMapping(value = "/editschoolsuplies")
    @PreAuthorize("isAuthenticated()")
    public String editschoolsuplies(Model model, RedirectAttributes redirectAttributes,
                                    @RequestParam(name = "id") Long id,
                                    @RequestParam(name = "name") String name,
                                    @RequestParam(name = "price") int price,
                                    @RequestParam(name = "image") String image,
                                    @RequestParam(name = "description") String description,
                                    @RequestParam(name = "manufacturer") String manufacturer,
                                    @RequestParam(name = "country_id") Long countryId) {
        Countries countries = countryRepository.getOne(countryId);
        SchoolSuplies schoolSuplies = schoolSupliesService.getSchoolSupliesById(id);
        List<SchoolSuplies> allSchoolSuplies = schoolSupliesService.getAllSchoolSuplies();
        schoolSuplies.setName(name);
        schoolSuplies.setPrice(price);
        schoolSuplies.setImage(image);
        schoolSuplies.setDescription(description);
        schoolSuplies.setManufacturer(manufacturer);
        schoolSuplies.setCountry(countries);
        schoolSupliesService.saveSchoolSuplies(schoolSuplies);
        return "redirect:/schoolsuplies";
    }

    @PostMapping(value = "/editart")
    @PreAuthorize("isAuthenticated()")
    public String editart(Model model, RedirectAttributes redirectAttributes,
                          @RequestParam(name = "id") Long id,
                          @RequestParam(name = "name") String name,
                          @RequestParam(name = "price") int price,
                          @RequestParam(name = "image") String image,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "manufacturer") String manufacturer,
                          @RequestParam(name = "country_id") Long countryId) {
        Countries countries = countryRepository.getOne(countryId);
        Art art = artService.getArtById(id);
        List<Art> allArt = artService.getAllArt();
        art.setName(name);
        art.setPrice(price);
        art.setImage(image);
        art.setDescription(description);
        art.setManufacturer(manufacturer);
        art.setCountry(countries);
        artService.saveArt(art);
        return "redirect:/art";
    }


    @PostMapping(value = "/addbook")
    @PreAuthorize("isAuthenticated()")
    public String addBook(@RequestParam(name = "name") String name,
                          @RequestParam(name = "price") int price,
                          @RequestParam(name = "genre") String genre,
                          @RequestParam(name = "image") String image,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "author") String author,
                          @RequestParam(name = "country_id") Long countryId
    ) {
        Book book = new Book();

        book.setName(name);
        book.setPrice(price);
        book.setGenre(genre);
        book.setImage(image);
        book.setDescription(description);
        book.setAuthor(author);
        book.setAddedDate(new Date());
        Countries countries = countryRepository.getOne(countryId);
        book.setCountry(countries);

        bookService.saveCourse(book);
        return "redirect:/";
    }

    @PostMapping(value = "/addschoolsuplies")
    @PreAuthorize("isAuthenticated()")
    public String addSchoolSuplies(@RequestParam(name = "name") String name,
                                   @RequestParam(name = "price") int price,
                                   @RequestParam(name = "image") String image,
                                   @RequestParam(name = "description") String description,
                                   @RequestParam(name = "manufacturer") String manufacturer,
                                   @RequestParam(name = "country_id") Long countryId
    ) {
        SchoolSuplies schoolSuplies = new SchoolSuplies();

        schoolSuplies.setName(name);
        schoolSuplies.setPrice(price);
        schoolSuplies.setImage(image);
        schoolSuplies.setDescription(description);
        schoolSuplies.setManufacturer(manufacturer);
        schoolSuplies.setAddedDate(new Date());
        Countries countries = countryRepository.getOne(countryId);
        schoolSuplies.setCountry(countries);

        schoolSupliesService.saveCourse(schoolSuplies);
        return "redirect:/";
    }

    @PostMapping(value = "/addart")
    @PreAuthorize("isAuthenticated()")
    public String addArt(@RequestParam(name = "name") String name,
                         @RequestParam(name = "price") int price,
                         @RequestParam(name = "image") String image,
                         @RequestParam(name = "description") String description,
                         @RequestParam(name = "manufacturer") String manufacturer,
                         @RequestParam(name = "country_id") Long countryId
    ) {
        Art art = new Art();

        art.setName(name);
        art.setPrice(price);
        art.setImage(image);
        art.setDescription(description);
        art.setManufacturer(manufacturer);
        art.setAddedDate(new Date());
        Countries countries = countryRepository.getOne(countryId);
        art.setCountry(countries);

        artService.saveCourse(art);
        return "redirect:/";
    }

    @GetMapping(value = "/detailBook/{bookId}")
    public String details(@PathVariable(name = "bookId") Long id, Model model) {
        model.addAttribute("currentUser", getUser());
        Book book = bookService.getBookById(id);
        ArrayList<Comments> comments = (ArrayList<Comments>) commentService.findAllByBook(book);
        if (comments != null) {
            model.addAttribute("comments", comments);
        }
        if (book != null) {
            model.addAttribute("book", book);
        }

        return "detailBook";
    }

    @GetMapping(value = "/detailSchool/{schoolSuppliesId}")
    public String detailsSchoolSupplies(@PathVariable(name = "schoolSuppliesId") Long id, Model model) {
        model.addAttribute("currentUser", getUser());
        SchoolSuplies schoolSupplies = schoolSupliesService.getSchoolSupliesById(id);
        ArrayList<Comments> comments = (ArrayList<Comments>) commentService.findAllBySchoolSupplies(schoolSupplies);
        if (comments != null) {
            model.addAttribute("comments", comments);
        }
        if (schoolSupplies != null) {
            model.addAttribute("schoolSupplies", schoolSupplies);
        }

        return "detailSchool";
    }

    @GetMapping(value = "/detailArt/{ArtId}")
    public String detailsArt(@PathVariable(name = "ArtId") Long id, Model model) {
        model.addAttribute("currentUser", getUser());
        Art art = artService.getArtById(id);
        ArrayList<Comments> comments = (ArrayList<Comments>) commentService.findAllByArt(art);
        if (comments != null) {
            model.addAttribute("comments", comments);
        }
        if (art != null) {
            model.addAttribute("art", art);
        }

        return "detailArt";
    }

//    BASKET BASKET BASKET BASKET BASKET

    @PostMapping(value = "/addToBasketBook")
    @PreAuthorize("isAuthenticated()")
    public String addToBasket(Model model,
                              @RequestParam(name = "book_id") Long id,
                              HttpServletRequest request) {
        Book book = bookService.getBookById(id);
        HttpSession session = request.getSession();
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        if (basket != null) {
            boolean has = false;
            for (Baskets b : basket) {
                if (b.getBook() != null) {
                    if (b.getBook().getId().equals(book.getId())) {
                        has = true;
                        int am = b.getAmount();
                        b.setAmount(am += 1);
                    }
                }
            }
            if (has == false) {
                basket.add(new Baskets(null, null, 1, book, null, null));
            }
            session.setAttribute("basket", basket);
        } else {
            basket = new ArrayList<>();
            basket.add(new Baskets(null, null, 1, book, null, null));
            session.setAttribute("basket", basket);
        }
        System.out.println(basket.toString());
        return "redirect:/";
    }

    @PostMapping(value = "/addToBasketArt")
    @PreAuthorize("isAuthenticated()")
    public String addToBasketArt(Model model,
                                 @RequestParam(name = "art_id") Long id,
                                 HttpServletRequest request) {
        Art art = artService.getArtById(id);
        HttpSession session = request.getSession();
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        if (basket != null) {
            boolean has = false;
            for (Baskets b : basket) {
                if (b.getArt() != null) {
                    if (b.getArt().getId().equals(art.getId())) {
                        has = true;
                        int am = b.getAmount();
                        b.setAmount(am += 1);
                    }
                }
            }
            if (has == false) {
                basket.add(new Baskets(null, null, 1, null, art, null));
            }
            session.setAttribute("basket", basket);
        } else {
            basket = new ArrayList<>();
            basket.add(new Baskets(null, null, 1, null, art, null));
            session.setAttribute("basket", basket);
        }
        System.out.println(basket.toString());
        return "redirect:/";
    }

    @PostMapping(value = "/addToBasketSchoolSupplies")
    @PreAuthorize("isAuthenticated()")
    public String addToBasketSchoolSupplies(Model model,
                                            @RequestParam(name = "schoolsupplies_id") Long id,
                                            HttpServletRequest request) {
        SchoolSuplies schoolSuplies = schoolSupliesService.getSchoolSupliesById(id);
        HttpSession session = request.getSession();
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        if (basket != null) {
            boolean has = false;
            for (Baskets b : basket) {
                if (b.getSchoolSupplies() != null) {
                    if (b.getSchoolSupplies().getId().equals(schoolSuplies.getId())) {
                        has = true;
                        int am = b.getAmount();
                        b.setAmount(am += 1);
                    }
                }
            }
            if (has == false) {
                basket.add(new Baskets(null, null, 1, null, null, schoolSuplies));
            }
            session.setAttribute("basket", basket);
        } else {
            basket = new ArrayList<>();
            basket.add(new Baskets(null, null, 1, null, null, schoolSuplies));
            session.setAttribute("basket", basket);
        }
        System.out.println(basket.toString());
        return "redirect:/";
    }

    @GetMapping(value = "/showBasket")
    public String showBasket(Model model, HttpServletRequest request) {
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        if (basket != null) {
            for (int i = 0; i < basket.size(); i++) {
                if (basket.get(i).getAmount() == 0) {
                    basket.remove(i);
                }

            }
        }
        model.addAttribute("basket", basket);
        int max = 0;
        if (basket != null) {
            for (Baskets b : basket) {
                if (b.getBook() != null) {
                    max += (b.getBook().getPrice() * b.getAmount());
                }
            }
            for (Baskets b : basket) {
                if (b.getArt() != null) {
                    max += (b.getArt().getPrice() * b.getAmount());
                }
            }
            for (Baskets b : basket) {
                if (b.getSchoolSupplies() != null) {
                    max += (b.getSchoolSupplies().getPrice() * b.getAmount());
                }
            }
        }

        int s = 0;
        if (basket != null) {
            for (int i = 0; i < basket.size(); i++) {
                s += basket.get(i).getAmount();
            }
        }
        model.addAttribute("baskets", s);
        model.addAttribute("currentUser", getUser());
        model.addAttribute("total", max);
        return "showBasket";
    }


    @PostMapping(value = "/increaseBook")
    @PreAuthorize("isAuthenticated()")
    public String increaseBook(@RequestParam(name = "book_id") Long bookId, HttpServletRequest request) {
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        HttpSession session = request.getSession();
        for (Baskets bas : basket) {
            if (bas.getBook() != null) {
                if (bas.getBook().getId().equals(bookId)) {
                    bas.setAmount(bas.getAmount() + 1);
                }
            }
        }
        session.setAttribute("basket", basket);
        return "redirect:/showBasket";
    }

    @PostMapping(value = "/decreaseBook")
    @PreAuthorize("isAuthenticated()")
    public String decreaseBook(@RequestParam(name = "book_id") Long bookId, HttpServletRequest request) {
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        HttpSession session = request.getSession();
        for (Baskets bas : basket) {
            if (bas.getBook() != null) {
                if (bas.getBook().getId().equals(bookId)) {
                    if (bas.getAmount() > 0) {
                        bas.setAmount(bas.getAmount() - 1);
                    }
                }
            }
        }
        session.setAttribute("basket", basket);
        return "redirect:/showBasket";
    }

    @PostMapping(value = "/increaseArt")
    @PreAuthorize("isAuthenticated()")
    public String increaseArt(@RequestParam(name = "art_id") Long artId, HttpServletRequest request) {
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        HttpSession session = request.getSession();
        for (Baskets bas : basket) {
            if (bas.getArt() != null) {
                if (bas.getArt().getId().equals(artId)) {
                    bas.setAmount(bas.getAmount() + 1);
                }
            }
        }
        session.setAttribute("basket", basket);
        return "redirect:/showBasket";
    }

    @PostMapping(value = "/decreaseArt")
    @PreAuthorize("isAuthenticated()")
    public String decreaseArt(@RequestParam(name = "art_id") Long artId, HttpServletRequest request) {
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        HttpSession session = request.getSession();
        for (Baskets bas : basket) {
            if (bas.getArt() != null) {
                if (bas.getArt().getId().equals(artId)) {
                    if (bas.getAmount() > 0) {
                        bas.setAmount(bas.getAmount() - 1);
                    }
                }
            }
        }
        session.setAttribute("basket", basket);
        return "redirect:/showBasket";
    }

    @PostMapping(value = "/increaseSchoolSupplies")
    @PreAuthorize("isAuthenticated()")
    public String increaseSchoolSupplies(@RequestParam(name = "schoolSupplies_id") Long schoolSuppliesId, HttpServletRequest request) {
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        HttpSession session = request.getSession();
        for (Baskets bas : basket) {
            if (bas.getSchoolSupplies() != null) {
                if (bas.getSchoolSupplies().getId().equals(schoolSuppliesId)) {
                    bas.setAmount(bas.getAmount() + 1);
                }
            }
        }
        session.setAttribute("basket", basket);
        return "redirect:/showBasket";
    }

    @PostMapping(value = "/decreaseSchoolSupplies")
    @PreAuthorize("isAuthenticated()")
    public String decreaseSchoolSupplies(@RequestParam(name = "art_id") Long schoolSuppliesId, HttpServletRequest request) {
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        HttpSession session = request.getSession();
        for (Baskets bas : basket) {
            if (bas.getSchoolSupplies() != null) {
                if (bas.getSchoolSupplies().getId().equals(schoolSuppliesId)) {
                    if (bas.getAmount() > 0) {
                        bas.setAmount(bas.getAmount() - 1);
                    }
                }

            }
        }
        session.setAttribute("basket", basket);
        return "redirect:/showBasket";
    }

    @PostMapping(value = "/clearBasket")
    @PreAuthorize("isAuthenticated()")
    public String clearBasket(HttpServletRequest request) {
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        HttpSession session = request.getSession();
        basket.clear();
        session.setAttribute("basket", basket);
        return "redirect:/showBasket";
    }

    @GetMapping(value = "/checkIn")
    public String checkIn(HttpServletRequest request) {
        ArrayList<Baskets> basket = (ArrayList<Baskets>) request.getSession().getAttribute("basket");
        HttpSession session = request.getSession();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        for (Baskets b : basket) {
            b.setDateOfBuy(date);
            basketService.addBaskets(b);
        }
        basket.clear();
        session.setAttribute("basket", basket);
        return "redirect:/showBasket";
    }

//-------------------------------------------------------------------


    @PostMapping(value = "/addCommentBook")
    @PreAuthorize("isAuthenticated()")
    public String addComment(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "book_id") Long bookId,
            @RequestParam(name = "comment") String comment) {
        Book book = bookService.getBookById(bookId);
        Users user = getUser();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        commentService.addComment(new Comments(null, comment, date, book, null, null, user));
        return "redirect:/detailBook/" + book.getId();
    }

    @PostMapping(value = "/editCommentBook")
    @PreAuthorize("isAuthenticated()")
    public String editComment(Model model,
                              @RequestParam(name = "comment_id") Long comment_id,
                              @RequestParam(name = "book_id") Long book_id,
                              @RequestParam(name = "comment") String comment) {
        Users user = getUser();
        Comments comments = commentService.getComment(comment_id);
        comments.setComment(comment);
        commentService.saveComment(comments);
        return "redirect:/detailBook/" + book_id;
    }

    @PostMapping(value = "/deleteCommentBook")
    @PreAuthorize("isAuthenticated()")
    public String deleteComment(Model model,
                                @RequestParam(name = "comment_id") Long comment_id,
                                @RequestParam(name = "book_id") Long book_id) {
        Comments comment = commentService.getComment(comment_id);
        commentService.deleteComment(comment);
        return "redirect:/detailBook/" + book_id;
    }

    //    -------------------------------------------------
    @PostMapping(value = "/addCommentArt")
    @PreAuthorize("isAuthenticated()")
    public String addCommentArt(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "art_id") Long artId,
            @RequestParam(name = "comment") String comment) {
        Art art = artService.getArtById(artId);
        Users user = getUser();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        commentService.addComment(new Comments(null, comment, date, null, art, null, user));
        return "redirect:/detailArt/" + art.getId();
    }

    @PostMapping(value = "/editCommentArt")
    @PreAuthorize("isAuthenticated()")
    public String editCommentArt(Model model,
                                 @RequestParam(name = "comment_id") Long comment_id,
                                 @RequestParam(name = "art_id") Long art_id,
                                 @RequestParam(name = "comment") String comment) {
        Users user = getUser();
        Comments comments = commentService.getComment(comment_id);
        comments.setComment(comment);
        commentService.saveComment(comments);
        return "redirect:/detailArt/" + art_id;
    }

    @PostMapping(value = "/deleteCommentArt")
    @PreAuthorize("isAuthenticated()")
    public String deleteCommentArt(Model model,
                                   @RequestParam(name = "comment_id") Long comment_id,
                                   @RequestParam(name = "art_id") Long art_id) {
        Comments comment = commentService.getComment(comment_id);
        commentService.deleteComment(comment);
        return "redirect:/detailArt/" + art_id;
    }

//    ------------------------------------------------------------------------

    @PostMapping(value = "/addCommentSchool")
    @PreAuthorize("isAuthenticated()")
    public String addCommentSchool(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "school_id") Long schoolId,
            @RequestParam(name = "comment") String comment) {
        SchoolSuplies schoolSuplies = schoolSupliesService.getSchoolSupliesById(schoolId);
        Users user = getUser();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        commentService.addComment(new Comments(null, comment, date, null, null, schoolSuplies, user));
        return "redirect:/detailArt/" + schoolSuplies.getId();
    }

    @PostMapping(value = "/editCommentSchool")
    @PreAuthorize("isAuthenticated()")
    public String editCommentSchool(Model model,
                                    @RequestParam(name = "comment_id") Long comment_id,
                                    @RequestParam(name = "school_id") Long school_id,
                                    @RequestParam(name = "comment") String comment) {
        Users user = getUser();
        Comments comments = commentService.getComment(comment_id);
        comments.setComment(comment);
        commentService.saveComment(comments);
        return "redirect:/detailArt/" + school_id;
    }

    @PostMapping(value = "/deleteCommentSchool")
    @PreAuthorize("isAuthenticated()")
    public String deleteCommentSchool(Model model,
                                      @RequestParam(name = "comment_id") Long comment_id,
                                      @RequestParam(name = "school_id") Long school_id) {
        Comments comment = commentService.getComment(comment_id);
        commentService.deleteComment(comment);
        return "redirect:/detailArt/" + school_id;
    }

//    --------------------------------------------

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (Users) authentication.getPrincipal();
        } else {
            return null;
        }
    }

}
