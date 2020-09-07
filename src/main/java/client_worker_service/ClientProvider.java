package client_worker_service;


public final class ClientProvider{
    final private String              name;
    final private String              surname;

    private String                    phoneNumber;
    private String                    email;
    final private String              companyName;

    ClientProvider(String name,
                   String surname,
                   String phoneNumber,
                   String email,
                   String companyName) {
        this.name = name;
        this.surname = surname;


        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;

        validateEmail(email);
        this.email = email;

        this.companyName = companyName;
    }

    private static void validatePhoneNumber(String phoneNumber){
        String regex = "\\d{3}-\\d{3}-\\d{4}";
        if (!phoneNumber.matches(regex))
            throw new IllegalArgumentException("Number must match pattern: ddd-ddd-dddd: " + phoneNumber);
    }

    private static void validateEmail(String email){
        String regex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
        if (!email.matches(regex))
            throw new IllegalArgumentException("Email must match pattern: " +
                    "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]: " + email);
    }

    //this variable \ method will be used in analytics service
    public void changePhoneNumber(String phoneNumber){
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    //this variable \ method will be used in analytics service
    public void changeEmail(String email){
        validateEmail(email);
        this.email = email;
    }

    public boolean sameCompany(String anotherCompany){
        return companyName.equals(anotherCompany);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumbers=" + phoneNumber +
                ", emails=" + email +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
