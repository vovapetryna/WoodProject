package client_worker_service;

final public class Contacts {
    private String phoneNumber;
    private String email;

    public Contacts(String phoneNumber,
                    String email){
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;

        validateEmail(email);
        this.email = email;
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

    public void setPhoneNumber(String phoneNumber){
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email){
        validateEmail(email);
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
