
import java.util.ArrayList;
import java.util.List;

import io.qase.api.exceptions.QaseException;
import io.qase.client.ApiClient;
import io.qase.client.Configuration;
import io.qase.client.api.PlansApi;
import io.qase.client.api.ResultsApi;
import io.qase.client.api.RunsApi;
import io.qase.client.model.PlanDetailedAllOfCases;
import io.qase.client.model.ResultCreate;
import io.qase.client.model.ResultCreateSteps;
import io.qase.client.model.RunCreate;
import org.junit.Test;

public class TestQaseWorkshop {
    ApiClient apiClient = Configuration.getDefaultApiClient();

    @Test
    public void getTestCaseFromTestPlan() throws QaseException {
        apiClient.setApiKey("c6b09b2aee57af3baa2edb493df18af6e787d631");

        PlansApi plansApi = new PlansApi(apiClient);

        List<PlanDetailedAllOfCases> planDetailedAllOfCases =
                plansApi.getPlan("WEBAPP", 22).getResult().getCases();

        List<Long> testCaseIds = new ArrayList<>();

        for (PlanDetailedAllOfCases singleCase: planDetailedAllOfCases) {
            testCaseIds.add(singleCase.getCaseId());
        }
        System.out.println(testCaseIds.size());
        // Test plan 22: 61 test cases
    }
    @Test
    public void createTestRun() throws QaseException {
        apiClient.setApiKey("c6b09b2aee57af3baa2edb493df18af6e787d631");

        PlansApi plansApi = new PlansApi(apiClient);

        List<PlanDetailedAllOfCases> planDetailedAllOfCases =
                plansApi.getPlan("WEBAPP", 22).getResult().getCases();

        List<Long> testCaseIds = new ArrayList<>();


        for (PlanDetailedAllOfCases singleCase: planDetailedAllOfCases) {
            testCaseIds.add(singleCase.getCaseId());
        }

        //Create Test run
        RunsApi runsApi = new RunsApi(apiClient);

        RunCreate runCreate = new RunCreate()
                .title("Test run for test plan 22")
                .cases(testCaseIds)
                .description("This is for Workshop");
        runsApi.createRun("WEBAPP",runCreate);
    }
    @Test
    public void AddTestResultToTestRun() throws QaseException {
        apiClient.setApiKey("c6b09b2aee57af3baa2edb493df18af6e787d631");

        ResultsApi resultsApi = new ResultsApi(apiClient);

        Long testCaseId = 6L;
        int testRunId = 499;

        List<ResultCreateSteps> testStepResult = new ArrayList<>();

        ResultCreateSteps stepsOne = new ResultCreateSteps().position(1)
                .status(ResultCreateSteps.StatusEnum.FAILED).comment("Fail Test");

        ResultCreateSteps stepsTwo = new ResultCreateSteps().position(2)
                .status(ResultCreateSteps.StatusEnum.PASSED).comment("Pass Test");

        testStepResult.add(stepsOne);
        testStepResult.add(stepsTwo);

        ResultCreate resultCreate = new ResultCreate().caseId(testCaseId).status(ResultCreate.StatusEnum.PASSED)
                .comment("There are two steps having result").steps(testStepResult);

        resultsApi.createResult("WEBAPP",testRunId,resultCreate);


    }
}
