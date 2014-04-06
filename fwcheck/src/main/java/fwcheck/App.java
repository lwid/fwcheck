package fwcheck;

import fwcheck.drools.RuleDB;
import fwcheck.pojo.FwRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("Hello World");

        // Bootstrap Spring
        logger.info("Initializing Spring context.");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring.xml");
        logger.info("Requesting ruleDb bean from Spring");
        RuleDB ruleDB = (RuleDB) applicationContext.getBean("ruleDb");

        // TBD: Refactor package drools => rules.drools
        // TBD: Create package rules.neo
        // TBD: Create meta-checker in rules packages and invoke that one instead
        // TBD: All these three could implement the same interface potentially (E.g. RuleDb)

        // Run some simple tests
        logger.info("Creating test rule");
        FwRule fwRule = new FwRule("10.10.0.0", 4711, "10.10.0.1", 80);
        assert( fwRule.isValid() == false);

        logger.info("Verifying test rule");
        ruleDB.verifyRule(fwRule);
        assert( fwRule.isValid() == true);

        logger.info("Verifying modified rule");
        fwRule.setDport(81);
        ruleDB.verifyRule(fwRule);
        assert( fwRule.isValid() == false);

        logger.info("DONE.");
    }
}
