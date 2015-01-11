package com.blogspot.pbetkier.collections.utilities;

import com.blogspot.pbetkier.Person;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.blogspot.pbetkier.PersonBuilder.person;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IterablesIteratorsTest {

    @Captor
    private ArgumentCaptor<Iterable<String>> captor;

    @Test
    public void shouldCreateTableRowsSwitchingBetweenBackgroundColors_JDK() {
        // given
        Person antek = person().name("Antek").build();
        Person basia = person().name("Basia").build();
        Person celina = person().name("Celina").build();
        List<Person> persons = Arrays.asList(antek, basia, celina);

        // when
        List<TableRow> rows = new ArrayList<>();
        for (int i = 0; i < persons.size(); ++i) {
            rows.add(new TableRow(persons.get(i).getName()));
            if (i % 2 == 0) {
                rows.get(i).setBackground(Color.BLUE);
            } else {
                rows.get(i).setBackground(Color.GREEN);
            }
        }

        // then
        assertThat(rows).extracting("text")
                .containsExactly("Antek", "Basia", "Celina");
        assertThat(rows).extracting("background")
                .containsExactly(Color.BLUE, Color.GREEN, Color.BLUE);
    }

    @Test
    public void shouldCreateTableRowsSwitchingBetweenBackgroundColors_Guava() {
        // given
        Person antek = person().name("Antek").build();
        Person basia = person().name("Basia").build();
        Person celina = person().name("Celina").build();
        List<Person> persons = Lists.newArrayList(antek, basia, celina);

        // when
        Iterator<Color> backgroundProvider = Iterators.cycle(Color.BLUE, Color.GREEN);
        List<TableRow> rows = Lists.newArrayList();
        for (Person person : persons) {
            rows.add(new TableRow(person.getName(), backgroundProvider.next()));
        }

        // then
        assertThat(rows).extracting("text")
                .containsExactly("Antek", "Basia", "Celina");
        assertThat(rows).extracting("background")
                .containsExactly(Color.BLUE, Color.GREEN, Color.BLUE);
    }

    @Test
    public void shouldSendPersonalNotificationsInBatches() {
        // given
        List<String> toNotify = Lists.newArrayList("Ania", "Basia", "Celina", "Danuta", "Ewa");
        NotificationService notificationService = mock(NotificationService.class);

        // when
        for (List<String> personsBatch : Iterables.partition(toNotify, 2)) {
            notificationService.notifyPersons(personsBatch);
        }

        // then
        verify(notificationService, times(3)).notifyPersons(captor.capture());
        assertThat(Iterables.concat(captor.getAllValues())).containsExactlyElementsOf(toNotify);
    }

}
