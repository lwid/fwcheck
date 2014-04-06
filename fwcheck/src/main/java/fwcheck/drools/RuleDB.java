package fwcheck.drools;

import fwcheck.pojo.FwRule;

/**
 * Created with IntelliJ IDEA.
 * User: Common
 * Date: 2014-04-06
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public interface RuleDB {
    void verifyRule(FwRule r);
}
