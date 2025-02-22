package net.minestom.server.entity.pathfinding;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.chunk.ChunkUtils;
import net.minestom.testing.Env;
import net.minestom.testing.EnvTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@EnvTest
public class PathfinderIntegrationTest {

    /**
     * Validate that the path is valid
     * Currently only checks to make sure path is not null, and that nodes are not inside blocks
     *
     * @param nodes the nodes to validate
     * @return true if the path is valid
     */
    private boolean validateNodes(List<PNode> nodes, Instance instance) {
        if (nodes == null) fail("Path is null");
        if (nodes.size() == 0) fail("Path is empty");

        nodes.forEach((node) -> {
            if (instance.getBlock(node.point).isSolid()) {
                fail("Node is inside a block");
            }
        });

        return true;
    }

    @Test
    public void testTall(Env env) {
        var i = env.createFlatInstance();
        i.getWorldBorder().setCenter(0, 0);
        i.getWorldBorder().setDiameter(10000);

        ChunkUtils.forChunksInRange(0, 0, 10, (x, z) -> {
            i.loadChunk(x, z).join();
        });

        var zombie = new LivingEntity(EntityType.ZOMBIE);
        zombie.setInstance(i, new Pos(0, 40, 0));
        zombie.setBoundingBox(3f, 6.5f, 3f);

        i.setBlock(1, 46, 7, Block.STONE);

        Navigator nav = new Navigator(zombie);
        nav.setPathTo(new Pos(0, 40, 10));
        while (nav.getState() == PPath.PathState.CALCULATING) {
        }

        assert (nav.getNodes() != null);
        validateNodes(nav.getNodes(), i);
    }

    @Test
    public void testStraightLine(Env env) {
        var i = env.createFlatInstance();
        i.getWorldBorder().setCenter(0, 0);
        i.getWorldBorder().setDiameter(10000);

        ChunkUtils.forChunksInRange(0, 0, 10, (x, z) -> {
            i.loadChunk(x, z).join();
        });

        var zombie = new LivingEntity(EntityType.ZOMBIE);
        zombie.setInstance(i, new Pos(0, 40, 0));

        Navigator nav = new Navigator(zombie);
        nav.setPathTo(new Pos(0, 40, 10));
        while (nav.getState() == PPath.PathState.CALCULATING) {
        }

        assert (nav.getNodes() != null);
        validateNodes(nav.getNodes(), i);
    }

    @Test
    public void testShort(Env env) {
        var i = env.createFlatInstance();
        i.getWorldBorder().setCenter(0, 0);
        i.getWorldBorder().setDiameter(10000);

        ChunkUtils.forChunksInRange(0, 0, 10, (x, z) -> {
            i.loadChunk(x, z).join();
        });

        var zombie = new LivingEntity(EntityType.ZOMBIE);
        zombie.setInstance(i, new Pos(0, 40, 0));

        Navigator nav = new Navigator(zombie);
        nav.setPathTo(new Pos(2, 40, 2));

        while (nav.getState() == PPath.PathState.CALCULATING) {
        }

        assert (nav.getNodes() != null);
        validateNodes(nav.getNodes(), i);
    }

    @Test
    public void testPFNodeEqual(Env env) {
        PNode node1 = new PNode(new Pos(0.777, 0, 0), 2, 0, PNode.NodeType.WALK, null);
        PNode node2 = new PNode(new Pos(0.777, 0, 0), 0, 3, PNode.NodeType.WALK, node1);

        Set<PNode> nodes = new HashSet<>();
        nodes.add(node1);
        nodes.add(node2);

        assert node1.equals(node2);
        assert nodes.size() == 1;
    }

    @Test
    public void testStraightLineBlocked(Env env) {
        var i = env.createFlatInstance();
        i.getWorldBorder().setCenter(0, 0);
        i.getWorldBorder().setDiameter(10000);

        ChunkUtils.forChunksInRange(0, 0, 10, (x, z) -> {
            i.loadChunk(x, z).join();
        });

        i.setBlock(-6, 40, 5, Block.STONE);
        i.setBlock(-5, 40, 5, Block.STONE);
        i.setBlock(-4, 40, 5, Block.STONE);
        i.setBlock(-3, 40, 5, Block.STONE);
        i.setBlock(-2, 40, 5, Block.STONE);
        i.setBlock(-1, 40, 5, Block.STONE);
        i.setBlock(0, 40, 5, Block.STONE);
        i.setBlock(1, 40, 5, Block.STONE);
        i.setBlock(2, 40, 5, Block.STONE);
        i.setBlock(3, 40, 5, Block.STONE);
        i.setBlock(4, 40, 5, Block.STONE);
        i.setBlock(5, 40, 5, Block.STONE);
        i.setBlock(6, 40, 5, Block.STONE);
        i.setBlock(7, 40, 5, Block.STONE);

        i.setBlock(-6, 41, 5, Block.STONE);
        i.setBlock(-5, 41, 5, Block.STONE);
        i.setBlock(-4, 41, 5, Block.STONE);
        i.setBlock(-3, 41, 5, Block.STONE);
        i.setBlock(-2, 41, 5, Block.STONE);
        i.setBlock(-1, 41, 5, Block.STONE);
        i.setBlock(0, 41, 5, Block.STONE);
        i.setBlock(1, 41, 5, Block.STONE);
        i.setBlock(2, 41, 5, Block.STONE);
        i.setBlock(3, 41, 5, Block.STONE);
        i.setBlock(4, 41, 5, Block.STONE);
        i.setBlock(5, 41, 5, Block.STONE);
        i.setBlock(6, 41, 5, Block.STONE);
        i.setBlock(7, 41, 5, Block.STONE);

        var zombie = new LivingEntity(EntityType.ZOMBIE);
        zombie.setInstance(i, new Pos(0, 40, 0));
        zombie.setBoundingBox(zombie.getBoundingBox().expand(4f, 4f, 4f));

        Navigator nav = new Navigator(zombie);
        nav.setPathTo(new Pos(0, 40, 10));
        while (nav.getState() == PPath.PathState.CALCULATING) {
        }

        assert (nav.getNodes() != null);
        validateNodes(nav.getNodes(), i);
    }
}
