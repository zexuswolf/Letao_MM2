Feature: Material Batch Job

	Scenario: Mass Create Material Batch Job
		Given csv file "D:\MyRepo\Letao_MM\src\test\resources\test_batch.csv"
		When execute job : createMaterialBatchJob
		Then the job should be "COMPLETED"