<template>
    <div>
        <h3>Reviews</h3>

        <!-- Add a Review -->
        <form @submit.prevent="submitReview" class="flex flex-col">
            <v-rating
                color="white"
                empty-icon="mdi-star-outline"
                 full-icon="mdi-star"
                 half-icon="mdi-star-half-full"
                hover
                length="5"
                size="32"
                v-model="selectedRating"
             />
            <textarea v-model="reviewText" placeholder="Write a review..." required rows="3" class="border p-2 my-2"></textarea>
            <button type="submit" class="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded-full transition-colors duration-300">Submit</button>
        </form>

        <!-- Display Reviews -->
        <ul>
            <li v-for="review in reviews" :key="review.id">
                <p><strong>{{ review.username }}</strong> ({{ review.rating }}★): {{ review.reviewText }}</p>
                <button v-if="review.username === currentUser" @click="deleteReview(review.id)">Delete</button>
            </li>
        </ul>
    </div>
</template>

<script>
    import apiClient from "../api/axios";
    export default {
        props: {
            contentId: Number,
            contentType: String, // "MOVIE" or "MUSIC"
        },
        data() {
            return {
                reviews: [],
                selectedRating: 5,
                reviewText: "",
                currentUser: localStorage.getItem("username"),
            };
        },
        async mounted() {
            await this.fetchReviews();
        },
        methods: {
            async fetchReviews() {
                try {
                    const response = await apiClient.get(`/reviews/${this.contentType}/${this.contentId}`);
                    this.reviews = response.data;
                } catch (error) {
                    console.error("Failed to fetch reviews");
                }
            },
            async submitReview() {
                try {
                    await apiClient.post("/reviews", {
                        contentId: this.contentId,
                        contentType: this.contentType,
                        rating: this.selectedRating,
                        reviewText: this.reviewText,
                    });
                    this.reviewText = "";
                     await this.fetchReviews();
                     this.$emit("review-updated")
                } catch (error) {
                    console.error("Failed to submit review");
                }
            },
            async deleteReview(reviewId) {
                try {
                    await apiClient.delete(`/reviews/${reviewId}`);
                     await this.fetchReviews();
                     this.$emit("review-updated")
                } catch (error) {
                    console.error("Failed to delete review");
                }
            },
        },
    };
</script>