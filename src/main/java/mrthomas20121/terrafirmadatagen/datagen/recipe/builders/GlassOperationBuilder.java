package mrthomas20121.terrafirmadatagen.datagen.recipe.builders;

import net.dries007.tfc.common.capabilities.glass.GlassOperation;

import java.util.ArrayList;
import java.util.List;

public class GlassOperationBuilder {

    private List<GlassOperation> operations = new ArrayList<>();

    public GlassOperationBuilder operation(GlassOperation operation) {
        this.operations.add(operation);
        return this;
    }

    public List<GlassOperation> build() {
        return operations;
    }
}
