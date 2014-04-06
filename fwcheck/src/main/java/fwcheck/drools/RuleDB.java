package fwcheck.drools;

import fwcheck.pojo.FwRule;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Common
 * Date: 2014-04-06
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */

public class RuleDB{

    private KnowledgeBase kbase;

    public RuleDB() {

        // Load the generic ruleset(TBD: path to drools file should be an argument)
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newClassPathResource("/generic.drl", getClass()), ResourceType.DRL );
        if ( kbuilder.hasErrors() ) {
            System.err.println( kbuilder.getErrors().toString() );
        }
        kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
    }

    public void verifyRule(FwRule r) {
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        ksession.execute( r );
    }

}
