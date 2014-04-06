package fwcheck;

import fwcheck.pojo.FwRule;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        // Iterate all rules
        // Check if rule match generic Drools ruleset
        // Check if there is a direct match in the exception neo4j graph
        // Else print an warning about non-allowed rule


        // Load the generic ruleset
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newClassPathResource("generic.drl", App.class.getClass()), ResourceType.DRL );
        if ( kbuilder.hasErrors() ) {
            System.err.println( kbuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );

        // Run a test (this should be a TEST stupid)
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        FwRule fwRule = new FwRule("10.10.0.0", 4711, "10.10.0.1", 80);
        assert( fwRule.isValid() == false);
        ksession.execute( fwRule );
        assert( fwRule.isValid() == true);
        fwRule.setDport(81);
        ksession.execute( fwRule );
        assert( fwRule.isValid() == false);


        System.out.println("OMGLOL");

    }
}
