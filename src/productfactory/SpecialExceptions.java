package productfactory;
/**
 * hat alle unterklassen mit selbstgemachten exceptions
 * @author Jason Donner
 */
abstract class SpecialExceptions{
	/*
	 * exception für falschen Typ
	 */
	static class ExceptionFalseType extends Exception{
	}
	/*
	 * exception für leere Usereingabe
	 */
	static class ExceptionEmptyString extends Exception{
	}
}
