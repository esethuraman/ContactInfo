package contacts;

import contacts.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContactRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Contact> getAllContacts() {

        return jdbcTemplate.query(
                "select id, firstName, lastName, phoneNumer, emailAddress " +
                        "from contacts order by lastName",
                new RowMapper<Contact>() {
                    @Override
                    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {

                        Contact contact = new Contact();
                        contact.setId(resultSet.getLong(1));
                        contact.setFirstName(resultSet.getString(2));
                        contact.setLastName(resultSet.getString(3));
                        contact.setPhoneNumber(resultSet.getString(4));
                        contact.setEmailAddress(resultSet.getString(5));

                        return contact;
                    }
                }
        );
    }

    public void save(Contact contact) {
        jdbcTemplate.update(
          "insert into contacts " +
          "(firstName, lastName, phoneNumber, emailAddress) " +
          "values(?, ?, ?, ?)" ,
          contact.getFirstName(),
          contact.getLastName(),
          contact.getPhoneNumber(),
          contact.getEmailAddress()
        );
    }
}
