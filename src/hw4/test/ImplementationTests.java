package hw4.test;

import hw5.test.EdgeTest;
import hw5.test.GraphTest;
import hw5.test.NodeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * ImplementationTest is a simple test suite to test the implementation of each
 * homework.  You are not required to modify this file for Homework 4.
 */

@RunWith(Suite.class)
@SuiteClasses({
	GraphTest.class,
	NodeTest.class,
	EdgeTest.class
	})
public final class ImplementationTests
{
  // This class is a placeholder for the suite, so it has no members.
  // The @SuiteClasses annotation lists the elements of the suite.
}
