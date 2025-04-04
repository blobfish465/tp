package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTenants.ALICE;
import static seedu.address.testutil.TypicalTenants.getTypicalTenantTracker;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.tenant.Tenant;
import seedu.address.testutil.TenantBuilder;

public class TenantTrackerTest {

    private final TenantTracker tenantTracker = new TenantTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tenantTracker.getTenantList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tenantTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTenantTracker_replacesData() {
        TenantTracker newData = getTypicalTenantTracker();
        tenantTracker.resetData(newData);
        assertEquals(newData, tenantTracker);
    }

    // @Test
    // public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
    // // Two persons with the same identity fields
    // Tenant editedAlice =
    // new TenantBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)/* .withTags(VALID_TAG_HUSBAND)
    // */.build();
    // List<Tenant> newPersons = Arrays.asList(ALICE, editedAlice);
    // TenantTrackerStub newData = new TenantTrackerStub(newPersons);

    // assertThrows(DuplicatePersonException.class, () -> tenantTracker.resetData(newData));
    // }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tenantTracker.hasTenant(null));
    }

    @Test
    public void hasPerson_personNotInTenantTracker_returnsFalse() {
        assertFalse(tenantTracker.hasTenant(ALICE));
    }

    @Test
    public void hasPerson_personInTenantTracker_returnsTrue() {
        tenantTracker.addTenant(ALICE);
        assertTrue(tenantTracker.hasTenant(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInTenantTracker_returnsTrue() {
        tenantTracker.addTenant(ALICE);
        Tenant editedAlice =
                new TenantBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)/* .withTags(VALID_TAG_HUSBAND) */.build();
        assertTrue(tenantTracker.hasTenant(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tenantTracker.getTenantList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = TenantTracker.class.getCanonicalName() + "{tenants=" + tenantTracker.getTenantList() + "}";
        assertEquals(expected, tenantTracker.toString());
    }

    // /**
    //  * A stub ReadOnlyTenantTracker whose persons list can violate interface constraints.
    //  */
    // private static class TenantTrackerStub implements ReadOnlyTenantTracker {
    //     private final ObservableList<Tenant> persons = FXCollections.observableArrayList();

    //     TenantTrackerStub(Collection<Tenant> persons) {
    //         this.persons.setAll(persons);
    //     }

    //     @Override
    //     public ObservableList<Tenant> getTenantList() {
    //         return persons;
    //     }
    // }

}
