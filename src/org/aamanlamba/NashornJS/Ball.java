/**
 * 
 */
package org.aamanlamba.NashornJS;

/**
 * @author aamanlamba
 * Functional interface example - has a single abstract function
 *  Since this is Java 8, also has an additional non-abstract function with a default method
 */
public interface Ball {
	/**
	 * Abstract function - provide implementation
	 */
	void hit();
	/**
	 * Default implementation of count()
	 */
	default void count() {
		System.out.println("Default count implementation");
	}
}
