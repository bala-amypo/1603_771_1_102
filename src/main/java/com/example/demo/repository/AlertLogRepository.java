public interface AlertLogRepository extends JpaRepository<AlertLog, Long> {
    List<AlertLog> findByWarrantyId(Long warrantyId);
}
