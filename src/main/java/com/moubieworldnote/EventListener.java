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

package com.moubieworldnote;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author MouBieCat
 */
public final class EventListener implements Listener {

    @EventHandler
    public void onJoin(final @NotNull PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final World world = player.getWorld();

        final String songName = MouBieCat.getInstance().getWorldSongManager().getSongName(world);

        if (songName != null)
            MouBieCat.getInstance().getSongsManager().playSongToPlayer(player, songName);
    }

    @EventHandler
    public void onQuit(final @NotNull PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        MouBieCat.getInstance().getSongsManager().stopSongToPlayer(player);
    }

    @EventHandler
    public void onChangedWorld(final @NotNull PlayerChangedWorldEvent event) {
        final Player player = event.getPlayer();
        final World fromWorld = event.getFrom();
        final World nowWorld = player.getWorld();

        MouBieCat.getInstance().getSongsManager().stopSongToPlayer(player);

        final String songName = MouBieCat.getInstance().getWorldSongManager().getSongName(nowWorld);

        if (songName != null)
            MouBieCat.getInstance().getSongsManager().playSongToPlayer(player, songName);
    }

}
