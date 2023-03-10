import entity.AgeEntity;
import entity.GeographicareaEntity;
import entity.HouseholdEntity;
import entity.TotalincomeEntity;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Objects;

public class Main {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    public static void main(String[] args){
        // Question 5
        displayNumberOfRecords();
        // Question 6
        criteriaQuery();

        // Close Entity Manager Factory
        entityManagerFactory.close();
    }

    // Question 5: The function is used to display total number of records with 2016 Canada Census
    public static void displayNumberOfRecords(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // a: One couple census family without other persons in the household
        String aQuery = "SELECT COUNT(*) FROM HouseholdEntity h " +
                "JOIN CensusyearEntity c ON h.censusYear = c.censusYearId " +
                "JOIN HouseholdtypeEntity ct ON h.householdType = ct.id " +
                "Where c.censusYear=2016 AND ct.description='One couple census family without other persons in the household'";
        // b: 2 or more members in the household
        String bQuery = "SELECT COUNT(*) FROM HouseholdEntity h " +
                "JOIN CensusyearEntity c ON h.censusYear = c.censusYearId " +
                "JOIN HouseholdsizeEntity cs ON h.householdSize = cs.id " +
                "Where c.censusYear=2016 AND cs.description='2 or more persons'";
        // c: At least 1 earner in the household
        String cQuery = "SELECT COUNT(*) FROM HouseholdEntity h " +
                "JOIN CensusyearEntity c ON h.censusYear = c.censusYearId " +
                "JOIN HouseholdearnersEntity he ON h.householdEarners = he.id " +
                "Where c.censusYear=2016 AND he.description='1 earner or more'";
        // d: Total income between $80,000 and $89,999
        String dQuery = "SELECT COUNT(*) FROM HouseholdEntity h " +
                "JOIN CensusyearEntity c ON h.censusYear = c.censusYearId " +
                "JOIN TotalincomeEntity ti ON h.totalIncome = ti.id " +
                "Where c.censusYear=2016 AND ti.description='$80,000 to $89,999'";

        try{
            System.out.println("Question 5-a: "+entityManager.createQuery(aQuery).getSingleResult().toString());
            System.out.println("Question 5-b: "+entityManager.createQuery(bQuery).getSingleResult().toString());
            System.out.println("Question 5-c: "+entityManager.createQuery(cQuery).getSingleResult().toString());
            System.out.println("Question 5-d: "+entityManager.createQuery(dQuery).getSingleResult().toString());
        } catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        } finally {
            // Close Entity Manager
            entityManager.close();
        }
    }

    // Question 6: The function is used to use Criteria Query to get result
    public static void criteriaQuery(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        try{
            // a. Use Multiselect to get Code, Level and Name from Geographic Area Table. Display 10 Records only
            CriteriaQuery<Tuple> cqa = criteriaBuilder.createTupleQuery();
            Root<GeographicareaEntity> roota = cqa.from(GeographicareaEntity.class);
            cqa.multiselect(roota.get("code"), roota.get("level"), roota.get("name"));
            List<Tuple> aList = entityManager.createQuery(cqa).setMaxResults(10).getResultList();
            System.out.println("\nQuestion 6-a: ");
            System.out.printf("%-4s %-5s %-10s %n","Code","Level","Name");
            aList.forEach(geo->System.out.printf("%-4s %-5s %-10s %n",geo.get(0), geo.get(1), geo.get(2)));

            // b. Display Top 20 combined Age information from Age Table order by Desc
            CriteriaQuery<AgeEntity> cqb = criteriaBuilder.createQuery(AgeEntity.class);
            Root<AgeEntity> rootb = cqb.from(AgeEntity.class);
            cqb.select(rootb);
            cqb.orderBy(criteriaBuilder.desc(rootb.get("combined")));
            List<AgeEntity> bList = entityManager.createQuery(cqb).setMaxResults(20).getResultList();
            System.out.println("\nQuestion 6-b: ");
            System.out.printf("%-5s %-8s %-10s %-13s %-8s %-8s %-8s %n",
                    "AgeID","AgeGroup","CensusYear","GeographicArea","Combined","Male","Female");
            bList.forEach(age->System.out.printf("%-5s %-8s %-10s %-14s %-8s %-8s %-8s %n",
                    age.getAgeId(),age.getAgeGroup(),age.getCensusYear(),age.getGeographicArea(),age.getGeographicArea(),age.getCombined(),age.getMale(),age.getFemale()));

            // c. Use Where Clause to Display information for Geographic Area named ‘Peterborough’
            CriteriaQuery<GeographicareaEntity> cqc = criteriaBuilder.createQuery(GeographicareaEntity.class);
            Root<GeographicareaEntity> rootc = cqc.from(GeographicareaEntity.class);
            cqc.select(rootc);
            cqc.where(criteriaBuilder.equal(rootc.get("name"),"Peterborough"));
            List<GeographicareaEntity> cList = entityManager.createQuery(cqc).getResultList();
            System.out.println("\nQuestion 6-c: ");
            System.out.printf("%-16s %-4s %-5s %-10s %-15s %n",
                    "GeographicAreaID","Code","Level","Name","AlternativeCode");
            cList.forEach(geo->System.out.printf("%-16s %-4s %-5s %-15s %-15s %n",
                    geo.getGeographicAreaId(),geo.getCode(),geo.getLevel(),geo.getName(),geo.getAlternativeCode()));

            // d. Display Total Income Description between id 10 to 20
            CriteriaQuery<Tuple> cqd = criteriaBuilder.createTupleQuery();
            Root<TotalincomeEntity> rootd = cqd.from(TotalincomeEntity.class);
            cqd.select(rootd.get("description"));
            cqd.where(criteriaBuilder.between(rootd.get("id"),10,20));
            List<Tuple> dList = entityManager.createQuery(cqd).getResultList();
            System.out.println("\nQuestion 6-d: ");
            System.out.printf("%-10s %n","Description");
            dList.forEach(income->System.out.printf("%-10s %n",income.get(0)));

            // e. Use Group by Clause to Display Geographic Area Information group by Level
            CriteriaQuery<Tuple> cqe = criteriaBuilder.createTupleQuery();
            Root<GeographicareaEntity> roote = cqe.from(GeographicareaEntity.class);
            cqe.multiselect(roote.get("level"),criteriaBuilder.count(roote.get("id")));
            cqe.groupBy(roote.get("level"));
            List<Tuple> eList = entityManager.createQuery(cqe).getResultList();
            System.out.println("\nQuestion 6-e: ");
            System.out.printf("%-5s %-4s %n","Level","Count");
            eList.forEach(geo->System.out.printf("%-5s %-4s %n",geo.get(0),geo.get(1)));

        } catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        } finally {
            // Close Entity Manager
            entityManager.close();
        }
    }
}
