package com.habr.telegrambotmfa.repositories;

import com.habr.telegrambotmfa.models.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SettingsRepository extends JpaRepository<Settings, Integer> {

    Optional<Settings> getSettingsByKey(String key);
}
