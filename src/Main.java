import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Engine {
    private String pedalSize;

    public Engine(String pedalSize) {
        this.pedalSize = pedalSize;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "pedalSize='" + pedalSize + '\'' +
                '}';
    }
}

class Car {
    private static int idCounter = 1;
    private int id;
    private Engine engine;

    public Car(String pedalSize) {
        this.id = idCounter++;
        this.engine = new Engine(pedalSize);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", engine=" + engine +
                '}';
    }
}

class Customer {
    private String name;
    private Car car;

    public Customer(String name) {
        this.name = name;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", car=" + (car != null ? car : "No car assigned") +
                '}';
    }
}

class FactoryAF {
    private List<Car> cars = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void saleCar() {
        Iterator<Customer> customerIterator = customers.iterator();
        Iterator<Car> carIterator = cars.iterator();

        while (customerIterator.hasNext() && carIterator.hasNext()) {
            Customer customer = customerIterator.next();
            Car car = carIterator.next();
            customer.setCar(car);
            carIterator.remove();
        }

        if (!cars.isEmpty()) {
            cars.clear();
        }
    }

    public void displayCars() {
        System.out.println("Cars in stock:");
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
        } else {
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }

    public void displayCustomers() {
        System.out.println("Customers:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        FactoryAF factory = new FactoryAF();

        factory.addCar(new Car("Small"));
        factory.addCar(new Car("Medium"));
        factory.addCar(new Car("Large"));

        factory.addCustomer(new Customer("Alice"));
        factory.addCustomer(new Customer("Bob"));
        factory.addCustomer(new Customer("Charlie"));
        factory.addCustomer(new Customer("Diana"));

        System.out.println("Before Sale:");
        factory.displayCars();
        factory.displayCustomers();

        factory.saleCar();

        System.out.println("\nAfter Sale:");
        factory.displayCars();
        factory.displayCustomers();
    }
}
