import org.example.Dto.Data;
import org.example.Dto.Type;
import org.example.Service.DroolsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DroolsRuleUnitTest {

    private KieSession kieSession;

    @BeforeEach
    void setup() {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/rules_test.drl"));
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();

        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        KieBaseConfiguration kieBaseConfiguration = kieServices.newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);

        KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);
        kieSession = kieBase.newKieSession();
    }

    @Test
    void testRuleOne_SevereInfectionOrSepsis() {
        int patientId = 1;
        Date timestamp = new Date();

        Data fever = new Data(101, patientId, Type.TEMPERATURE, 39.0, null, timestamp);
        kieSession.insert(fever);
        Data tachycardia = new Data(102, patientId, Type.SPO2, null, 110.0, timestamp);
        kieSession.insert(tachycardia);
        Data hypotension = new Data(103, patientId, Type.BLOOD_PRESSURE, 100.0, 60.0, timestamp);
        kieSession.insert(hypotension);
        Data lowSpo2 = new Data(104, patientId, Type.SPO2, 93.0, null, timestamp);
        kieSession.insert(lowSpo2);

        int rulesFired = kieSession.fireAllRules(match -> match.getRule().getName().equals("rule-1-SevereInfectionOrSepsis"));

        assertEquals(1, rulesFired, "Expected exactly one rule to fire indicating sepsis.");
    }

    @Test
    void testRuleTwo_ShockOrCirculatoryFailure() {
        int patientId = 1;
        Date timestamp = new Date();

        Data hypotension = new Data(201, patientId, Type.BLOOD_PRESSURE, 100.0, 60.0, timestamp);
        Data tachycardia = new Data(202, patientId, Type.SPO2, null, 110.0, timestamp);
        Data lowSpo2 = new Data(203, patientId, Type.SPO2, 93.0, null, timestamp);

        kieSession.insert(hypotension);
        kieSession.insert(tachycardia);
        kieSession.insert(lowSpo2);

        int rulesFired = kieSession.fireAllRules(match -> match.getRule().getName().equals("rule-2-ShockOrCirculatoryFailure"));

        assertEquals(1, rulesFired, "Expected exactly one rule to fire indicating shock or circulatory failure.");
    }

    @Test
    void testRuleThree_StressOrHypertension() {
        int patientId = 2;
        Date timestamp = new Date();

        Data hypertension = new Data(301, patientId, Type.BLOOD_PRESSURE, 140.0, 90.0, timestamp);
        Data tachycardia = new Data(302, patientId, Type.SPO2, null, 110.0, timestamp);

        kieSession.insert(hypertension);
        kieSession.insert(tachycardia);

        int rulesFired = kieSession.fireAllRules(match -> match.getRule().getName().equals("rule-3-StressOrHypertension"));

        assertEquals(1, rulesFired, "Expected exactly one rule to fire indicating stress or cardiovascular issues.");
    }

    @Test
    void testRuleFour_EarlyStageInflammatoryDisease() {
        int patientId = 3;
        Date timestamp = new Date();

        Data fever = new Data(401, patientId, Type.TEMPERATURE, 39.0, null, timestamp);
        Data hypotension = new Data(402, patientId, Type.BLOOD_PRESSURE, 100.0, 60.0, timestamp);
        Data normalSpo2 = new Data(403, patientId, Type.SPO2, 97.0, null, timestamp);

        kieSession.insert(fever);
        kieSession.insert(hypotension);
        kieSession.insert(normalSpo2);

        int rulesFired = kieSession.fireAllRules(match -> match.getRule().getName().equals("rule-4-EarlyStageInflammatoryDisease"));

        assertEquals(1, rulesFired, "Expected exactly one rule to fire indicating early-stage inflammatory disease.");
    }

    @Test
    void testRuleFive_HeartProblemsOrConductionDisorder() {
        int patientId = 4;
        Date timestamp = new Date();

        Data bradycardia = new Data(501, patientId, Type.SPO2, null, 45.0, timestamp);
        Data hypotension = new Data(502, patientId, Type.BLOOD_PRESSURE, 100.0, 60.0, timestamp);
        Data normalSpo2 = new Data(503, patientId, Type.SPO2, 97.0, null, timestamp);

        kieSession.insert(bradycardia);
        kieSession.insert(hypotension);
        kieSession.insert(normalSpo2);

        int rulesFired = kieSession.fireAllRules(match -> match.getRule().getName().equals("rule-5-HeartProblemsOrConductionDisorder"));

        assertEquals(1, rulesFired, "Expected exactly one rule to fire indicating heart problems or conduction disorder.");
    }
}
