package fwcheck.drools;

import fwcheck.pojo.FwRule;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

/**
 * Created with IntelliJ IDEA.
 * User: Common
 * Date: 2014-04-06
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */

public class RuleDBImpl implements RuleDB {

    private KnowledgeBase kbase;

    public RuleDBImpl() {

        // Load the generic ruleset(TBD: path to drools file should be an argument)
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newClassPathResource("/generic.drl", getClass()), ResourceType.DRL );
        if ( kbuilder.hasErrors() ) {
            System.err.println( kbuilder.getErrors().toString() );
        }
        kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
    }

    @Override
    public void verifyRule(FwRule r) {
        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
        ksession.execute( r );
    }

}
