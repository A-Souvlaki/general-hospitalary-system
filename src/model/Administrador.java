package model;

import excepciones.IncorrectPassWordException;

public class Administrador {
	
	//Attributes
	/**
		*this attribute contains the name of the manager
		*/
	private String name;
	/**
		*this attribute contains the id of the manager
		*/
	private int id;
	/**
		*this attribute contains the password of the manager
		*/
	private String password;
	/**
		*this attribute contains the address of the manager
		*/
	private String address;

//Methods
	//Builder
		/**
		*This is the constructor of the Manager class
		*@param the name of the costumer. name != null
		*@param the code of the costumer. id != null
		*@param the password of the costumer. password!= null
		*@param the address of the costumer. address != null
		*/
		public Administrador(String name, int id, String password, String address) {
			this.name = name;
			this.id = id;
			this.password = password;
			this.address = address;
			
		}
	//Getters
		/**
		 *this method allows get the manager name 
		 */
		public String getName() {
			return name;
		}
		/**
		 *this method allows get the manager id 
		 */
		public int getId() {
			return id;
		}
		/**
		 *this method allows get the manager password 
		 */
		public String getPassword() {
			return password;
		}
		/**
		 *this method allows get the manager address 
		 */
		public String getAddress() {
			return address;
		}
			
		
	//Setters
		/**
		 *this method allows change the name of the manager
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 *this method allows change the id of the manager
		 */
		public void setId(int id) {
			this.id = id;
		}
		/**
		 *this method allows change the password of the manager
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		/**
		 *this method allows change the address of the manager
		 */
		public void setAddress(String address) {
			this.address = address;
		}
	//operational
		/**
		 *this method return a boolean if the password is the same at the String in the param
		 *@param the possible password. posiblePassWord != null
		 */
		public boolean passWordCheck() throws IncorrectPassWordException{
			boolean flag = false;
			if(password.equals(password)) {
				flag = true;
			}else {
				throw new IncorrectPassWordException(IncorrectPassWordException.INCORRECT_INPUT);
			}
			return  flag;
		}
		/**
		 * this method works to know if the password entered by the user is corrct
		 * @param password the possible password to check.password !=null
		 * @return a boolean if the password is the correct or not
		 * @throws IncorrectPassWordException
		 */
		public boolean check(String password) throws IncorrectPassWordException {
			boolean flag = false;
			for(int i = 0; i<password.length();i++ ) {
				if(Character.isDigit(password.charAt(i))) {
					flag = true;
				}
			}
			if(flag == false) {
				throw new IncorrectPassWordException(IncorrectPassWordException.WRONG_FORMAT);
			}
			return flag;
		}
}
