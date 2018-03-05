package com.letao.mm2.job.create_material_batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import com.letao.mm2.model.Material;

@Configuration
@EnableBatchProcessing
public class CreateMaterialBatch {

	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    public DataSource dataSource;
    
    private String OVERRIDDEN_BY_EXPRESSION = "OVERRIDDEN_BY_EXPRESSION";
    
    @Bean("createMatrialBatch")
    public Job createMatrialBatch(CreateMaterialBatchCompletionNotificationListener listener) {
    	return jobBuilderFactory.get("createMaterialBatchJob")
    			.incrementer(new RunIdIncrementer())
    			.listener(listener)
    			.flow(createMatrialBatchStep())
    			.end()
    			.build();
    }
    
    @Bean("createMatrialBatchStep")
    public Step createMatrialBatchStep() {
    	return stepBuilderFactory.get("createMatrialBatchStep")
    			.<Material,Material> chunk(10)
    			.reader(createMatrialBatchStepReader(OVERRIDDEN_BY_EXPRESSION))
    			.writer(createMatrialBatchStepWriter())
    			.build();
    }
    
    @Bean("createMatrialBatchStepReader")
    @StepScope
    public FlatFileItemReader<Material> createMatrialBatchStepReader(@Value("#{jobParameters[file]}") String fname) {
    	FlatFileItemReader<Material> reader = new FlatFileItemReader();
        reader.setResource(new FileSystemResource(fname));
        //reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLineMapper(new DefaultLineMapper<Material>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "id", "thaiName","englishName","materialTypeId" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Material>() {{
                setTargetType(Material.class);
            }});
        }});
        return reader;
    }
    
    @Bean("createMatrialBatchStepWriter")
    public JdbcBatchItemWriter<Material> createMatrialBatchStepWriter() {
    	JdbcBatchItemWriter<Material> writer = new JdbcBatchItemWriter();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Material>());
        writer.setSql("insert into LETAO_MM_MATERIAL (ID,THAI_NAME,ENGLISH_NAME,MATERIAL_TYPE_ID,ACTIVE,CREATED_AT,UPDATED_AT) VALUES (:id, :thaiName,:englishName,:materialTypeId,1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)");
        writer.setDataSource(dataSource);
    	return writer;
    }
	
}
