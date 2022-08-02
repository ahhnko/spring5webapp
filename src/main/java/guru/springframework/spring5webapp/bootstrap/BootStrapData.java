package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Publisher elPubli = new Publisher();
		elPubli.setName("elpi");
		elPubli.setAddressLine1("asf");
		elPubli.setCity("bo");
		elPubli.setState("cun");
		elPubli.setZip("asfd421");

		publisherRepository.save(elPubli);

		System.out.println("Number of publishers: " + publisherRepository.count());

		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "123123");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		ddd.setPublisher(elPubli);
		elPubli.getBooks().add(ddd);

		authorRepository.save(eric);
		bookRepository.save(ddd);
		publisherRepository.save(elPubli);

		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "23452355");
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);

		noEJB.setPublisher(elPubli);
		elPubli.getBooks().add(noEJB);

		authorRepository.save(rod);
		bookRepository.save(noEJB);
		publisherRepository.save(elPubli);

		System.out.println("Started in Bootstrap");
		System.out.println("Number of Books: " + bookRepository.count());
		System.out.println("Publisher Number of books: " + elPubli.getBooks().size());
	}

}
