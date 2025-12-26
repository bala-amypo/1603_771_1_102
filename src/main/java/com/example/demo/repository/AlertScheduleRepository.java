public interface AlertScheduleRepository extends JpaRepository<AlertSchedule, Long> {
    List<AlertSchedule> findByWarrantyId(Long warrantyId);
}
