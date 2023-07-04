package ru.dev.A1.A1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dev.A1.A1.dao.JpaDAOHibernate;
import ru.dev.A1.A1.models.User;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    //private UserRepository repo;
    private JpaDAOHibernate repo;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
 /*   @Autowired
    public UserRepositoryUserDetailsService(UserRepository repo){
        this.repo = repo;
    }*/

    @Autowired
    public UserRepositoryUserDetailsService(JpaDAOHibernate repo){
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      /*  System.out.println(repo.getEntityManager().getMetamodel().getEntities().toString());

        String queryUser = "from USER_KEBAB_CLOUD where username = :paramName";
        Query query = repo.getEntityManager().createQuery(queryUser);
        query.setParameter("paramName",username);

        List<User> result = query.getResultList();
        User user = result.get(0);

        *//*if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(username + "not found");*/
        User user = repo.getUserByUsername(username);

        if(user == null)throw new UsernameNotFoundException(username + " not found");

        return user;
    }
}
