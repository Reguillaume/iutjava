<<<<<<< HEAD
package edu.iut.app;

public class CommandLineOption <ValueType> {
	
	public enum OptionType {
		NONE("None"),
		FILE("File"),
		STRING("String"),
		INTEGER("Integer"),
		DOUBLE("Double"),
		NOVALUE("NoValue");
		private String optionType;
		
		OptionType(String optionType) {
			this.optionType = optionType;
		}
		
		public String toString() {
			return optionType;
		}		
	}
	
	public CommandLineOption() {		
	}
	
	public CommandLineOption(final OptionType optionType, final String key, final String description, final ValueType defaultValue) {
		this.optionType=optionType;
		this.key=key;
		this.description=description;
		this.value=defaultValue;
		
	}
	
	public void setOption(OptionType optionType, String key, String description, ValueType defaultValue) {
		this.optionType=optionType;
		this.key=key;
		this.description=description;
		this.defaultValue=value;
	}
	
	public  void setValue(ValueType value) {
		this.value = value;
	}
		
	public String getKey() {
		return this.key;
	}
	public String getDescription() {
		return this.description;
	}
	public ValueType getValue() {
		if (value != null) {
			return value;
		}
		return defaultValue;
	}
	public OptionType getOptionType() {
		return optionType;
	}

	protected String key;
	protected String description;
	protected ValueType defaultValue;
	protected ValueType value;
	protected OptionType optionType;
	

}
=======
package edu.iut.app;

public class CommandLineOption <ValueType> {
	
	public enum OptionType {
		NONE("None"),
		FILE("File"),
		STRING("String"),
		INTEGER("Integer"),
		DOUBLE("Double"),
		NOVALUE("NoValue");
		private String optionType;
		
		OptionType(String optionType) {
			this.optionType = optionType;
		}
		
		public String toString() {
			return optionType;
		}		
	}
	
	public CommandLineOption() {		
	}
	
	public CommandLineOption(final OptionType optionType, final String key, final String description, final ValueType defaultValue) {
		this.optionType=optionType;
		this.key=key;
		this.description=description;
		this.value=defaultValue;
		
	}
	
	public void setOption(OptionType optionType, String key, String description, ValueType defaultValue) {
		this.optionType=optionType;
		this.key=key;
		this.description=description;
		this.defaultValue=value;
	}
	
	public  void setValue(ValueType value) {
		this.value = value;
	}
		
	public String getKey() {
		return this.key;
	}
	public String getDescription() {
		return this.description;
	}
	public ValueType getValue() {
		if (value != null) {
			return value;
		}
		return defaultValue;
	}
	public OptionType getOptionType() {
		return optionType;
	}

	protected String key;
	protected String description;
	protected ValueType defaultValue;
	protected ValueType value;
	protected OptionType optionType;
	

}
>>>>>>> 606d363c0a78d99f0fd25a55d8f1fb14a6ccb183
