package controllers;

import models.Pet;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class PetController {
    private final ArrayList<Pet> pets = new ArrayList<>();

/*CRUD functions*/
    /*CREATE*/
    @PostMapping("/api/pets")
    public Pet createPet(@RequestBody() Pet petRequest){
        Pet newPet = new Pet(
                petRequest.getName(),
                petRequest.getAge(),
                petRequest.getType(),
                petRequest.getOwnerName()
        );

        pets.add(newPet);
        return newPet;
    }

    /*READ*/
    @GetMapping("/api/pets")
    public ArrayList<Pet> getPets() {
        return pets;
    }

    /*UPDATE*/
    @PutMapping(path = "{id}")
    public void updatePetById(@PathVariable("id") UUID id, @RequestBody Pet pet) {
        Pet petForUpdate = selectPetById(id);
        int index = pets.indexOf(pet);
        if (petForUpdate != null){
            pets.set(index, pet);
            System.out.println(pet.getName() + " was updated successfully.");
        }
        System.out.println("Pet not found.");
    }

    /*DELETE*/
    @DeleteMapping(path = "{id}")
    public void deletePetById(@PathVariable("id") UUID id){
        Pet petForDelete = selectPetById(id);
        if (petForDelete == null){
            System.out.println("There is no Pet with such Id");
        }
        pets.remove(petForDelete);
        System.out.println(petForDelete.getName() + " was deleted successfully");
    }


    @GetMapping(path = "{id}")
    public Pet getPetById(@PathVariable("id") UUID id){
        return selectPetById(id);
    }

/*(@PathVariable("id") UUID id) --> grabbing id from @PathVariable("id"), and returning it to UUID id*/
    Pet selectPetById(UUID id) {
        for (Pet pet: pets){
            if (id.equals(pet.getId())){
                return pet;
            }
        }
        return null;
    }


}