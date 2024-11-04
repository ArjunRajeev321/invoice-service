//package com.invoice.invoice_service.flyway;
//
//import org.flywaydb.core.Flyway;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FlywayConfig {
//
//    @Bean
//    Flyway flyway() {
//        Flyway flyway = Flyway.configure()
//            .dataSource("jdbc:h2:mem:testdb", "sa", "")
//            .locations("classpath:db/migration")
//            .baselineOnMigrate(true)
//            .schemas("test_schema")
////            .sch
//            .load();
//
//        // Customize any additional Flyway settings here
//
//        return flyway;
//    }
//	
////	spring.flyway.locations=classpath:db/migration
////			spring.flyway.baseline-on-migrate=true
////			spring.flyway.schemas=your_schema
////			spring.flyway.sql-migration-prefix=V
////			spring.flyway.repeatable-sql-migration-prefix=R
//
//}
