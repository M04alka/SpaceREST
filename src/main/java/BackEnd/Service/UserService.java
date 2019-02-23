package BackEnd.Service;

import BackEnd.DTO.UserInfo;
import BackEnd.Dao.StreetInter;
import BackEnd.Dao.UsersRep;
import BackEnd.Model.Adress;
import BackEnd.Model.Street;
import BackEnd.Model.Users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import javax.naming.NotContextException;
import javax.validation.constraints.NotNull;

import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UsersRep usersRep;
    @Autowired
    StreetInter streetInter;

    public String add(@NotNull UserInfo userInfo) throws Exception {
        String street = userInfo.getStreet();
        String city = userInfo.getCity();
        String country = userInfo.getCountry();
        String firstname = userInfo.getFirstname();
        String lastName = userInfo.getLastname();
        try {
            if (adressValidation(street, city, country) && userIsExist(firstname, lastName)) {
                usersRep.save(new Users(firstname, lastName, new Adress(country, city, street)));
                return String.format("status: 201, " + "Message:User %s %s was created.", firstname, lastName);
            }
        }
        catch (NotAcceptableStatusException e) {
            return "status: 206, " + "message :" + "Adress country city street doesn't exist.";
        } catch (NotContextException e) {
            return "status: 204, " + "message :User firstname lastname already exists.";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    private boolean adressValidation(String street, String city_name, String City_Country_Name) throws Exception {
        Optional<Street> result = streetInter.findStreetByNameAndCity_NameAndCity_Country_Name(street, city_name, City_Country_Name);
        if (result.isPresent()) {
            return true;
        } else throw new NotAcceptableStatusException("");
    }

    public String findUser(@NotNull String firstname, @NotNull String lastname) {
        try {
            Optional<Users> user = usersRep.findUsersByFirstnameAndLastname(firstname, lastname);
            if (user.isPresent()) {
                return "status: 200, " +user.get().toString();
            } else return " {status: 204, message: User firstname lastname doesn't exist.}";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public boolean userIsExist(@NotNull String firstname, @NotNull String lastname) throws NotContextException {
            Optional<Users> user = usersRep.findUsersByFirstnameAndLastname(firstname, lastname);
            if (user.isPresent()) {
                throw new NotContextException("");
            }
            else return true;
        }
    }



