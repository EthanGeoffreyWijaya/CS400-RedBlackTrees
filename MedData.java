interface MedDataInterface {
	public String getName();

	public int getAge();

	public double getWeight();
}

class MedData implements MedDataInterface {
	private String name;
	private int age;
	private double weight;

	public MedData(int age, String name, double weight) {
		this.name = name;
		this.age = age;
		this.weight = weight;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public double getWeight() {
		return this.weight;
	}

	@Override
	public String toString() {
		return name + ", " + age + ", " + weight;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MedData) {
			return this.name == ((MedDataInterface) o).getName() && this.age == ((MedDataInterface) o).getAge()
					&& this.weight == ((MedDataInterface) o).getWeight();
		}
		return false;
	}
}