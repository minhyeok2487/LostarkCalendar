package lostark.calendar.domain.marketItems;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketItemRepository extends JpaRepository<MarketItem,Integer> {

	MarketItem findByName(String name);
}
