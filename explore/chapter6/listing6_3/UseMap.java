package chapter6.listing6_3;


public class UseMap {

	  public static void main(String[] args) {
	    Map<String, Toon> toons = new Map<String, Toon>()
	        .put("Mickey", new Toon("Myszka", "Miki", "mickey@disney.com"))
	        .put("Minnie", new Toon("Myszka", "Minnie"))
	        .put("Donald", new Toon("Kaczor", "Donald", "donald@disney.com"));

	    Option<String> mickey = toons.get("Mickey").flatMap(Toon::getEmail);
	    Option<String> minnie = toons.get("Minnie").flatMap(Toon::getEmail);
	    Option<String> goofy = toons.get("Goofy").flatMap(Toon::getEmail);

	    System.out.println(mickey.getOrElse(() -> "Brak danych"));
	    System.out.println(minnie.getOrElse(() -> "Brak danych"));
	    System.out.println(goofy.getOrElse(() -> "Brak danych"));

	    Option<String> mickey2 = toons.get("Mickey").map(Toon::getLastName);
	    Option<String> minnie2 = toons.get("Minnie").map(Toon::getLastName);
	    Option<String> goofy2 = toons.get("Goofy").map(Toon::getLastName);

	    System.out.println(mickey2.getOrElse(() -> "Brak danych 2"));
	    System.out.println(minnie2.getOrElse(() -> "Brak danych 2"));
	    System.out.println(goofy2.getOrElse(() -> "Brak danych 2"));
	  }

	  static class Toon {
	    private final String firstName;
	    private final String lastName;
	    private final Option<String> email;

	    Toon(String firstName, String lastName) {
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.email = Option.none();
	    }

	    Toon(String firstName, String lastName, String email) {
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.email = Option.some(email);
	    }


	    public String getLastName() {
	      return lastName;
	    }
	    
	    public Option<String> getEmail() {
	      return email;
	    }
	    
	  }
	}
