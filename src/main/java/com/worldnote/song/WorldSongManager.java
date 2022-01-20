/**
 * LICENSE
 * WorldNotePlugin
 * -------------
 * Copyright (C) 2022 MouBieCat(MouBie_Yuki)
 * -------------
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package com.worldnote.song;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MouBieCat
 */
public final class WorldSongManager {

    private final Map<World, String> worldSongManager = new HashMap<>();

    public void register(final @NotNull World world, final @NotNull String songName) {
        this.worldSongManager.put(world, songName);
    }

    public void unRegister(final @NotNull World world) {
        this.worldSongManager.remove(world);
    }

    @Nullable
    public String getSongName(final @NotNull World world) {
        return this.worldSongManager.get(world);
    }

}
