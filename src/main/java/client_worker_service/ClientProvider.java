package client_worker_service;


public final class ClientProvider{
    final private Person   person;
    final private Contacts contacts;
    private String         companyName;

    ClientProvider(Person person,
                   Contacts contacts,
                   String companyName) {
        this.person = person;
        this.contacts = contacts;
        this.companyName = companyName;
    }

    //this variable \ method will be used in analytics service
    public void changePhoneNumber(String phoneNumber){
        this.contacts.setPhoneNumber(phoneNumber);
    }

    //this variable \ method will be used in analytics service
    public void changeEmail(String email){
        this.contacts.setEmail(email);
    }

    public void changeCompanyName(String companyName){
        this.companyName = companyName;
    }

    public boolean sameCompany(String anotherCompany){
        return companyName.equals(anotherCompany);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "person='" + person + '\'' +
                ", contacts='" + contacts + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
