package com.adminportal.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	private String publisher;
	private String publicationDate;
	private String language;
	private String category;
	private int numberOfPages;
	private String format;
	private int isbn;
	private double shippingWeight;
	private double listPrice;
	private double ourPrice;
	private boolean active=true;
	
	@Column(columnDefinition="text")
	private String description;
	private int inStockNumber;
	
	@Transient
	private MultipartFile bookImage;
	
	
	@OneToMany(mappedBy = "book")
	@JsonIgnore
	private List<BookToCartItem> bookToCartItemList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public double getShippingWeight() {
		return shippingWeight;
	}

	public void setShippingWeight(double shippingWeight) {
		this.shippingWeight = shippingWeight;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public double getOurPrice() {
		return ourPrice;
	}

	public void setOurPrice(double ourPrice) {
		this.ourPrice = ourPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInStockNumber() {
		return inStockNumber;
	}

	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}

	public MultipartFile getBookImage() {
		return bookImage;
	}

	public void setBookImage(MultipartFile bookImage) {
		this.bookImage = bookImage;
	}

	public List<BookToCartItem> getBookToCartItemList() {
		return bookToCartItemList;
	}

	public void setBookToCartItemList(List<BookToCartItem> bookToCartItemList) {
		this.bookToCartItemList = bookToCartItemList;
	}
	
	
}

/*
***

---client

package com.infotech.client;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.infotech.entities.Person;
import com.infotech.entities.Phone;
import com.infotech.util.HibernateUtil;

public class HibernateNativeSQLClientTest {

	public static void main(String[] args) {
		//nativeQuerySelectEntitiesWithManyToOneAssociation();
		nativeQuerySelectEntitiesWithManyToOneAssociationUsingResultTransformer();
	}
	
	//Hibernate native query selecting entities with many-to-one association
	private static void nativeQuerySelectEntitiesWithManyToOneAssociation() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			List<Phone> phones = session.createNativeQuery(
				    "SELECT id, phone_number, phone_type, person_id " +
				    "FROM Phone" )
				.addEntity( Phone.class )
				.list();
			for (Phone phone : phones) {
				System.out.println("Phones Details::::::::::::::::::::::");
				System.out.println("Phone Id:"+phone.getId());
				System.out.println("Phone No:"+phone.getNumber());
				System.out.println("Phone Type:"+phone.getType().toString());
				Person person = phone.getPerson();
				if(person !=null){
					System.out.println("Person details:::::::::::::::::::::");
					System.out.println("Person Id:"+person.getId());
					System.out.println("Person Name:"+person.getName());
					System.out.println("Person NickName:"+person.getNickName());
					System.out.println("Address:"+person.getAddress());
					System.out.println("CreatedOn:"+person.getCreatedOn());
					System.out.println("Version:"+person.getVersion());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Hibernate native query selecting entities with joined many-to-one association and ResultTransformer
	private static void nativeQuerySelectEntitiesWithManyToOneAssociationUsingResultTransformer() {

		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			List<Person> persons = session.createNativeQuery(
				    "SELECT * " +
				    "FROM Phone ph " +
				    "LEFT JOIN Person pr ON ph.person_id = pr.id" )
				.addEntity("phone", Phone.class )
				.addJoin( "pr", "phone.person")
				.setResultTransformer( Criteria.ROOT_ENTITY )
				.list();

				for(Person person : persons) {
				    List<Phone> phones = person.getPhones();
				    System.out.println("Phone details::::::::::::::::::");
				    for (Phone phone : phones) {
				    	System.out.println("Phone Id:"+phone.getId());
						System.out.println("Phone No:"+phone.getNumber());
						System.out.println("Phone Type:"+phone.getType().toString());
						Person person2 = phone.getPerson();
						if(person2 != null){
							System.out.println("Person details:::::::::::::::::::::");
							System.out.println("Person Id:"+person.getId());
							System.out.println("Person Name:"+person.getName());
							System.out.println("Person NickName:"+person.getNickName());
							System.out.println("Address:"+person.getAddress());
							System.out.println("CreatedOn:"+person.getCreatedOn());
							System.out.println("Version:"+person.getVersion());
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}

---- models


package com.infotech.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="Person")
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="nickName")
    private String nickName;

    @Column(name="address")
    private String address;

    @Temporal(TemporalType.TIMESTAMP )
    @Column(name="createdOn")
    private Date createdOn;

    @Version
    @Column(name="version")
    private int version;
    
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @OrderColumn(name = "order_id")
    private List<Phone> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
}



package com.infotech.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Phone")
public class Phone {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_id")
    private Person person;

    @Column(name = "phone_number")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type")
    private PhoneType type;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Call> calls = new ArrayList<>(  );

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

	public List<Call> getCalls() {
		return calls;
	}

	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}

}


package com.infotech.entities;

public enum PhoneType {
	LAND_LINE,
    MOBILE;
}


package com.infotech.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone_call")
public class Call {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="phone_id")
    private Phone phone;

    @Column(name = "call_timestamp")
    private Date timestamp;

    @Column(name="duration")
    private int duration;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}

*/

