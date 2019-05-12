package com.dev.usersapi;

import com.dev.usersapi.repository.PersonRepository;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersapiApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(UsersapiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Summary> s = repository.getSummary();
//        for (Summary summary : s) {
//            System.out.println(summary);
//        }
        
        
//        for (String cpf : getCPFs()) {
//            User user = new User();
//            user.setCpf(cpf);
//            user.setName("user " + cpf);
//            user.setUf(getRandomUF());
//            repository.save(user);
//
//        }

    }

    public String[] getCPFs() {
        return new String[]{"213.536.452-58", "354.634.382-42", "884.280.250-61", "625.657.188-61", "514.750.001-46", "510.506.013-00", "736.577.231-02", "720.678.134-98", "823.074.713-08", "204.655.122-24", "825.007.765-22",
            "756.028.304-72", "676.706.042-98", "562.554.144-26", "828.202.714-64", "642.280.668-01", "361.317.662-90", "166.438.360-34", "641.883.281-77", "055.661.880-79", "656.641.185-20", "130.872.741-00", "148.052.627-45",
            "786.742.216-10", "236.727.713-33", "787.873.566-27", "836.684.348-32", "340.761.243-59", "002.171.057-00", "184.575.024-17", "438.361.238-51", "351.540.755-36", "731.548.776-06", "387.511.256-33", "350.285.220-06",
            "214.767.701-90", "676.185.845-32", "861.506.513-63", "836.035.282-80", "042.887.616-11", "071.545.403-03", "854.135.738-47", "136.131.131-20", "670.375.241-90", "788.163.476-60", "057.741.234-56", "345.213.020-76", "444.301.287-74", "335.028.771-90", "604.016.527-85"};
    }

    public String getRandomUF() {
        String[] ufs = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE",
            "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"};

        Random r = new Random(System.currentTimeMillis());
        return ufs[r.nextInt(ufs.length)];
    }

}
