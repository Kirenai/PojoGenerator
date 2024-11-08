import { Button } from "@/components/ui/button";
import { Box, VStack } from "@chakra-ui/react";
import { Link } from "react-router-dom";

const HomePage = () => {
  return (
    <VStack p="4">
      <Box
        mt="4"
        data-state="open"
        _open={{
          animationName: "fade-in, scale-in",
          animationDuration: "300ms",
        }}
      >
        <Link to={"/pojo"}>
          <Button variant="surface" colorPalette="teal" w="36" size="xl">
            Start Generator
          </Button>
        </Link>
      </Box>
    </VStack>
  );
};

export default HomePage;
