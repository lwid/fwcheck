package fwcheck;

import fwcheck.drools.RuleDB;
import fwcheck.pojo.FwRule;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;
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
