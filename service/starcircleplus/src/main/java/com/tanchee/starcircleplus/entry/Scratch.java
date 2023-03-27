/*
    private Entry updateTagsList(Entry entry, Set<Tag> tagList) {
        for (Tag tag : tagList) {
            Tag originalTag = tagRepository.findByName(tag.getName()).orElse(null);
            if( originalTag == null ) {
                logger.debug("TAG WITH NAME DOESNT ALREADY EXIST");
                tag.addEntry(entry);
                entry.addTag(tag);
                tagRepository.save(tag);
            } else {
                logger.debug("TAG WITH NAME EXISTS");
                entry.getTags().remove(tag);
                entry.getTags().add(originalTag);
                originalTag.addEntry(entry);
                //
            }
        }
        // DOES NOT WORK
        for (Tag tag : entry.getTags()) {
            if  ( tagList.contains(tag) ) { 
            } else { // if tag not in new taglist
                entry.getTags().remove(tag);
                tag.getEntries().remove(entry);
            }

        }
        return entry;
    }


    @Transactional
    public Entry save(Entry entry)
    {
        logger.debug("Attempting to save entry: {}", entry);

        if (entry.getId() == null) {
            logger.debug("ENTRY DOESNT ALREADY EXIST", entry);
            entry = updateTagsList(entry, entry.getTags());

            return entryRepository.save(entry);
        }

        Entry originalEntry = entryRepository.findById(entry.getId()).orElse(null);

        if (originalEntry == null) {
            logger.debug("ENTRY DOESNT ALREADY EXIST", entry);
            entry = updateTagsList(entry, entry.getTags());
            return entryRepository.save(entry);

        } else {
            logger.debug("ENTRY ALREADY EXISTS {}", originalEntry);
            originalEntry = updateTagsList(originalEntry, entry.getTags());
            for (Tag originalEntryTag : originalEntry.getTags()) {
            }
            return entryRepository.save(originalEntry);
        }
        //return entryRepository.save(entry);
    }

    @Transactional
    public Entry save(EntryDTO entryDTO) {
        Entry entry = new Entry();
        if( entryDTO.getId() == null ) { // entry with id does not exist
            for( String name : entryDTO.getTags() ) {
                Tag originalTag = tagRepository.findByName(name).orElse(null);
                //Tag tag;
                if( originalTag == null ) {
                    Tag tag = new Tag(name);
                    tag.addEntry(entry);
                    entry.addTag(tag);
                    tagRepository.save(tag);
                } else {
                    entry.addTag(originalTag);
                    originalTag.addEntry(entry);
                    tagRepository.save(originalTag);
                }
            }
        } else { // entry already exists
            entry = entryRepository.findById(entry.getId()).orElse(null);
            if (entry == null) {
                entry = new Entry();
            } else {
            }
        }
        entry.setId(entryDTO.getId());
        entry.setType(entryDTO.getType());
        entry.setChecked(entryDTO.isChecked());
        entry.setContent(entryDTO.getContent());

        return entryRepository.save(entry);
    }

    @Transactional
    public Entry update(Entry entry)
    {
        return entryRepository.save(entry);
    }
*/
